package com.example.task_system.auth.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthLoginResponse {

    @NotBlank(message = "VALIDATION.AUTH_LOGIN_RESPONSE.ACCESS_TOKEN.NOT_BLANK")
    @JsonProperty("access_token")
    private String accessToken;

    @NotBlank(message = "VALIDATION.AUTH_LOGIN_RESPONSE.REFRESH_TOKEN.NOT_BLANK")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @NotBlank(message = "VALIDATION.AUTH_LOGIN_RESPONSE.TOKEN_TYPE.NOT_BLANK")
    @JsonProperty("token_type")
    private String TokenType;

}
