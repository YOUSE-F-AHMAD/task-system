package com.example.task_system.auth.controller;

import com.example.task_system.auth.request.AuthLoginRequest;
import com.example.task_system.auth.request.AuthRegisterRequest;
import com.example.task_system.auth.response.AuthLoginResponse;
import com.example.task_system.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<AuthLoginResponse> login(
            @RequestBody AuthLoginRequest request
            )
    {
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<Void> register(
           @RequestBody AuthRegisterRequest request
           )
     {
           this.authService.register(request);
           return ResponseEntity.status(HttpStatus.CREATED).build();
     }

    @PostMapping("/api/v1/auth/token/refresh")
    public ResponseEntity<AuthLoginResponse> refreshToken(
            @RequestBody String refreshToken
            )
    {
        return ResponseEntity.ok(this.authService.refreshAccessToken(refreshToken));
    }

}
