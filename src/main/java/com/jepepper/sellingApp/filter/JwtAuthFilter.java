package com.jepepper.sellingApp.filter;

import com.jepepper.sellingApp.service.impl.ClientService;
import com.jepepper.sellingApp.service.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final ClientService userDetailsService;
  private final JWTUtils jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
          throws ServletException, IOException {

    final String authHeader = request.getHeader(AUTHORIZATION);
    final String username;
    final String jsonWebToken;
    if(request.getServletPath().equals("/client/login") || SecurityContextHolder.getContext().getAuthentication() != null){
      filterChain.doFilter(request,response);
      return;
    }
    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request,response);
      return;
    }
    jsonWebToken = authHeader.substring("Bearer ".length());
    username = jwtUtils.getUsernameFromToken(jsonWebToken);// TODO TO BE IMPLEMENTED
    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if(jwtUtils.validateToken(jsonWebToken,userDetails)){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    filterChain.doFilter(request,response);
  }
}
