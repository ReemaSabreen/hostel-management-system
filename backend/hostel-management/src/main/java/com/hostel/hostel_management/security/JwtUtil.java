package com.hostel.hostel_management.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    

    @Value("${JWT_SECRET}")
    private String SECRET;
    private Key key;

    
    @jakarta.annotation.PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    public String generateToken(String username, String role){
        return Jwts.builder()
                   .setSubject(username)
                   .claim("role", role)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                   .signWith(key)
                   .compact();
                   
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    public boolean validateToken(String token, String username){
        String extracted = extractUsername(token);
        return extracted.equals(username);
    }
    
}
