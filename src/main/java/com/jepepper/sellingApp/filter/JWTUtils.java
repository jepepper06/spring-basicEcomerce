package com.jepepper.sellingApp.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class JWTUtils {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("Enrique es marico".getBytes());
    private static final JWTVerifier JWT_VERIFIER = JWT.require(ALGORITHM).build();
    static String createToken(String subject, Integer expirationTime,String issuer, Collection<GrantedAuthority> authorities){

        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .withIssuer(issuer)
                .withClaim("roles",authorities
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())).
                sign(ALGORITHM);
        return token;
    }
    static void verifyProcessToken(String token){
        try{
            DecodedJWT decodedJWT = JWT_VERIFIER.verify(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }catch (Exception e){
            throw e;
        }
    }

}
