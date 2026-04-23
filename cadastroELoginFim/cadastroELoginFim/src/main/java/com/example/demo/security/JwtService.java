package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET="segredo_super_forte_com_mais_de_32_caracters";
    private final long EXP = 1000*60*60;

    private final Key key= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String gerarToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validarToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
