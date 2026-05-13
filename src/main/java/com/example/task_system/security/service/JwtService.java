package com.example.task_system.security.service;

import com.example.task_system.auth.response.AuthLoginResponse;
import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.security.KeyUtils;
import com.example.task_system.user.service.UserService;
import com.example.task_system.user.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    public static final String TOKEN_TYPE = "TOKEN_TYPE";


    private final UserService userService;

    private final PrivateKey privateKey;

    private final PublicKey publicKey;


    @Value("${app.security.expirationAccessToken}")
    private  long expirationAccessToken;

    @Value("${app.security.expirationRefreshToken}")
    private long expirationRefreshToken;

    public JwtService(UserService userService) throws Exception {
        this.userService = userService;
        this.privateKey = KeyUtils.loudPrivateKey("keys/local-only/private_key.pem");
        this.publicKey = KeyUtils.loudPublicKey("keys/local-only/public_key.pem");
    }

    public String generateAccessToken(final String username){
        Map<String,Object> claim = Map.of(TOKEN_TYPE,"ACCESS_TOKEN");
        return buildToken(username,claim,this.expirationAccessToken);
    }

    public String generateRefreshToken(final String username){
        Map<String,Object> claim = Map.of(TOKEN_TYPE,"REFRESH_TOKEN");
        return buildToken(username,claim,this.expirationRefreshToken);
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

    public boolean isTokenValid(String token , UserDetails userDetails){
        final String username = extractUsername(token);

        return userDetails.getUsername().equals(username) && !isTokenExpired(token);

    }
    public boolean isRefreshToken(String token){
        final String tokenType = extractClaims(token).get(TOKEN_TYPE).toString();
        return "REFRESH_TOKEN".equals(tokenType);
    }

    public boolean isAccessToken(String token){
        final String tokenType = extractClaims(token).get(TOKEN_TYPE).toString();
        return "ACCESS_TOKEN".equals(tokenType);
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


    public AuthLoginResponse refreshAccessToken(final String refreshToken){
        final Claims claims = extractClaims(refreshToken);
        var userDetails = this.userService.loadUserByUsername(claims.getSubject());
        if ( !(isTokenValid(refreshToken,userDetails))  || !isRefreshToken(refreshToken))
            throw new BusinessException(
                    ErrorCode.REFRESH_TOKEN_NOT_VALID);

        final String newAccessToken = generateAccessToken(claims.getSubject());
        final String newRefreshToken = generateRefreshToken(claims.getSubject());
        return AuthLoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .TokenType("Bearer")
                .build();
    }


}
