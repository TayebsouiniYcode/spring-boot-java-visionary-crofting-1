package com.youcode.visionarycrofting.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.youcode.visionarycrofting.service.UserService;
import com.youcode.visionarycrofting.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody Auth auth ){
        try{
            Authentication authentication = authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken (
                            auth.getUsername (),
                            auth.getPassword () ) );

            User user = (User) authentication.getPrincipal ();
            Algorithm algorithm = Algorithm.HMAC256 ( "secret".getBytes( ) );
            String access_token = JWT.create ( )
                    .withSubject ( user.getUsername () )
                    .withExpiresAt ( new Date (System.currentTimeMillis () + 10 * 60 * 1000) )
                    .withClaim ( "roles", user.getAuthorities ().stream ().map ( GrantedAuthority::getAuthority ).collect( Collectors.toList () ))
                    .sign ( algorithm );
            return ResponseEntity.ok ( access_token );
        } catch (Exception exception) {
            ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).body ( "Authentication failed" );
        }
        return null;
    }
}
