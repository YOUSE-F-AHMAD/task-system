package com.example.task_system.auth.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegisterRequest {

    @NotBlank(message = "VALIDATION.REGISTER.FIRSTNAME.NOT_BLANK")
    @Size(min = 2, max = 50, message = "VALIDATION.REGISTER.FIRSTNAME.NOT_BLANK")
    @Pattern(regexp = "^[\\p{L} '-]+$", message = "VALIDATION.REGISTER.FIRSTNAME.PATTERN")
    private String identifier;

    @NotBlank(message = "VALIDATION.REGISTER.EMAIL.NOT_BLANK")
    @Email
    private String email;

    @NotBlank(message = "VALIDATION.REGISTER.PASSWORD.NOT_BLANK")
    @Size(min = 8,max = 72,message = "VALIDATION.REGISTER.PASSWORD.SIZE")
    private String password;

    @NotBlank(message = "VALIDATION.REGISTER.PASSWORD.NOT_BLANK")
    @Size(min = 8,max = 72,message = "VALIDATION.REGISTER.PASSWORD.SIZE")
    private String confirmPassword;

}
