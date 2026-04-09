package com.example.task_system.user;

import com.example.task_system.auth.request.AuthLoginRequest;
import com.example.task_system.auth.request.AuthRegisterRequest;
import com.example.task_system.auth.response.AuthLoginResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class UserMapper {

    public Users toUser(AuthRegisterRequest registerRequest){
        return Users.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
    }
}
