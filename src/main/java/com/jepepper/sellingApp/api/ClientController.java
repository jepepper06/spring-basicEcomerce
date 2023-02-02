package com.jepepper.sellingApp.api;


import com.jepepper.sellingApp.service.impl.ClientService;
import com.jepepper.sellingApp.service.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(HttpServletRequest servletRequest,
            @RequestBody AuthRequest request){
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        final UserDetails user = clientService.loadUserByUsername(request.getUsername());
        if(user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user,60*60*24*30L));
        }
        return ResponseEntity.status(400).body("SOME ERROR HAS OCURRED");
    }
    @PostMapping("/new-password")
    public ResponseEntity<String> changePassword(HttpServletRequest request,String oldPassword, String newPassword) throws Exception {
        try{
            String userName = jwtUtils.getUsernameFromToken(request.getHeader(AUTHORIZATION));
            clientService.changePasswordToClient(userName, oldPassword, newPassword);
        }catch (Exception e){
            throw e;
        }

        return ResponseEntity.ok("Password changed succesfully");
    }
    @PostMapping("/registry")
    public ResponseEntity<String> register(String name, String userName, String password, String email){
        try {
            clientService.registryOfClient(name, userName, email, password);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Registry was not succesfull");
        }
        return ResponseEntity.ok().body("User was created succesfully");
    }
}
