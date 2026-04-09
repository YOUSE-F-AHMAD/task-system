package com.example.task_system.auth.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassword;


}
