package com.example.task_system.user;

import com.example.task_system.auth.request.AuthRegisterRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder encoder;

    public Users toUser(AuthRegisterRequest registerRequest){
        return Users.builder()
                .identifier(registerRequest.getIdentifier())
                .email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .build();
    }
}
