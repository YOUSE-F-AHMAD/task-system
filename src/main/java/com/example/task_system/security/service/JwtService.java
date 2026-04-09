package com.example.task_system.security.service;

import com.example.task_system.security.KeyUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

@Service
@Getter
public class JwtService {

    private static final String TOKEN_TYPE = "TOKEN_TYPE";

    private final PrivateKey privateKey;

    private final PublicKey publicKey;

    @Value("${app.security.expirationAccessToken}")
    private  long expirationAccessToken;

    @Value("${app.security.expirationAccessToken}")
    private long refreshAccessToken;

    public JwtService() throws Exception {
        this.privateKey = KeyUtils.loudPrivateKey("keys/local-only/private_key.pem");
        this.publicKey = KeyUtils.loudPublicKey("keys/local-only/public_key.pem");
    }

    public String generateAccessToken(final String username){
        Map<String,Object> claim = Map.of(TOKEN_TYPE,"ACCESS_TOKEN");
        return buildToken(username,claim,this.expirationAccessToken);
    }

    public String generateRefreshToken(final String username){
        Map<String,Object> claim = Map.of(TOKEN_TYPE,"REFRESH_TOKEN");
        return buildToken(username,claim,this.refreshAccessToken);
    }

    private String buildToken(String username, Map<String, Object> claim, long expirationAccessToken) {
        return Jwts.builder()
                .claims(claim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationAccessToken))
                .signWith(privateKey)
                .compact();
    }

    public boolean isTokenValid(String token , String expectedUsername){
        final String username = extractUsername(token);

        return expectedUsername.equals(username) && !isTokenExpired(token);

    }

    public boolean isTokenExpired(String token) {
         return  extractClaims(token).getExpiration()
                 .before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new RuntimeException("Error in extract claims",e);
        }
    }

    public String refreshAccessToken(final String refreshToken){
        final Claims claims = extractClaims(refreshToken);
        if (!"REFRESH_TOKEN".equals(claims.get(TOKEN_TYPE))){
            throw new RuntimeException("Error TOKEN_TYPE");
        }
        return generateRefreshToken(claims.getSubject());
    }



}
