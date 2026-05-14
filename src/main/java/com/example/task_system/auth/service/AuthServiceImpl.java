package com.example.task_system.auth.service;

import com.example.task_system.auth.request.AuthLoginRequest;
import com.example.task_system.auth.request.AuthRegisterRequest;
import com.example.task_system.auth.response.AuthLoginResponse;
import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.security.service.JwtService;
import com.example.task_system.user.Role;
import com.example.task_system.user.UserMapper;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Value("app.security.owner_email")
    private String ownerEmail;


    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        final Users users = (Users) auth.getPrincipal();
        final String token = this.jwtService.generateAccessToken(users.getUsername());
        final String refreshToken = this.jwtService.generateRefreshToken(users.getUsername());
        final String tokenType = "Bearer";

        return AuthLoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .TokenType(tokenType)
                .build();
    }

    @Override
    public AuthLoginResponse refreshAccessToken(String refreshToken) {
        return jwtService.refreshAccessToken(refreshToken);
    }

    @Override
    @Transactional
    public void register(AuthRegisterRequest request) {

    checkUserEmail(request.getEmail());
    checkPassword(request.getPassword(),request.getConfirmPassword());

    if (Objects.equals(request.getEmail(),ownerEmail)){
        final Users users = userMapper.toUser(request);
        users.setRole(Role.ADMEN);
        log.debug("saved user {} ", users);
        this.userRepository.save(users);
    }else{
        final Users users = userMapper.toUser(request);
        users.setRole(Role.USER);
        log.debug("saved user {} ", users);
        this.userRepository.save(users);
    }
   }

    private void checkUserEmail(String email) {
     if(this.userRepository.existsByEmailIgnoreCase(email)){
         throw new BusinessException(ErrorCode.YOUR_EMAIL_IS_EXISTS);
     }
    }

    private void checkPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)){
            throw new BusinessException(ErrorCode.UNCONFIRM_PASSWORD);
        }
    }
}
