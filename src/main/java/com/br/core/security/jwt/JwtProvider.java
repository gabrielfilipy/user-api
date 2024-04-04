package com.br.core.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.br.core.security.UserDatailImpl;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${auth.jwtSecret}")
    private String assinatura;

    @Value("${auth.jwtExpiration}")
    private int expiracao;

    public String geraTokenJWT(Authentication authentication) {
        UserDatailImpl userPrincipal = (UserDatailImpl) authentication.getPrincipal();
        final String roles = userPrincipal.getAuthorities().stream()
                .map(role -> {
                    return role.getAuthority();
                }).collect(Collectors.joining(","));

        String roles1 = Jwts.builder()
                .setSubject((userPrincipal.getId().toString()))
                .claim("roles", roles)
                .claim("nome", userPrincipal.getNome())
                .claim("matricula", userPrincipal.getMatricula())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiracao))
                .signWith(SignatureAlgorithm.HS512, assinatura)
                .compact();
        return roles1;
    }

    public String getSubjectJwt(String token) {
        return Jwts.parser().setSigningKey(assinatura).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwt(String authToken) {
        try {
            Jwts.parser().setSigningKey(assinatura).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Assinatura do token inválida!");
        } catch (MalformedJwtException e) {
            System.out.println("Token inválido!");
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado!");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token não suportado!");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }
}
