package com.example.task_system.auth.service;

import com.example.task_system.auth.request.AuthLoginRequest;
import com.example.task_system.auth.request.AuthRegisterRequest;
import com.example.task_system.auth.response.AuthLoginResponse;
import com.example.task_system.security.service.JwtService;
import com.example.task_system.user.Roles;
import com.example.task_system.user.UserMapper;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.RolesRope;
import com.example.task_system.user.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepo userRepository;

    private final RolesRope rolesRope;

    private final UserMapper userMapper;


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
    @Transactional
    public void register(AuthRegisterRequest request) {

    checkUserEmail(request.getEmail());
    checkPassword(request.getPassword(),request.getConfirmPassword());

    final Roles userRole = this.rolesRope.findByName("USER_ROLE")
            .orElseThrow(() -> new EntityNotFoundException("the role not found"));

    List<Roles> roles = new ArrayList<>();
    roles.add(userRole);

    final Users users = userMapper.toUser(request);
    users.setRoles(roles);
    log.debug("saved user {} ", users);
    this.userRepository.save(users);

    final List<Users> usersList = new ArrayList<>();
    usersList.add(users);
    userRole.setUsersList(usersList);

    this.rolesRope.save(userRole);

    }

    private void checkUserEmail(String email) {
     if(this.userRepository.existsByEmailIgnoreCase(email)){
         throw new RuntimeException("your email is exists");
     }
    }

    private void checkPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)){
            throw new RuntimeException("your password does not same conformPassword");
        }
    }
}
