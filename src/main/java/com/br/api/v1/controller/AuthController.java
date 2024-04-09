package com.br.api.v1.controller;

import com.br.api.v1.model.JwtModel;
import com.br.api.v1.model.input.LoginModelInput;
import com.br.core.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtModel> authenticationUser(@Valid @RequestBody LoginModelInput loginModelInput) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginModelInput.getMatricula(), loginModelInput.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.geraTokenJWT(authentication);
            return ResponseEntity.ok(new JwtModel(jwt));
        }catch(IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
            }
    }

}
