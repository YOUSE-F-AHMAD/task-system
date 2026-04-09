package com.example.task_system.auth.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthLoginRequest {

    private String email;

    private String password;
}
