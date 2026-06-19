package com.example.task_system.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePassWordRequest {

    @NotBlank(message = "VALIDATION.CHANGE_PASS_WORD_REQUEST.CURRENT_PASSWORD.NOT_BLANK")
    @Size(min = 8, message = "VALIDATION.CHANGE_PASS_WORD_REQUEST.CURRENT_PASSWORD.SIZE")
    private String currentPassword;

    @NotBlank(message = "VALIDATION.CHANGE_PASS_WORD_REQUEST.NEW_PASSWORD.NOT_BLANK")
    @Size(min = 8, message = "VALIDATION.CHANGE_PASS_WORD_REQUEST.NEW_PASSWORD.SIZE")
    private String newPassword;

    @NotBlank(message = "VALIDATION.CHANGE_PASS_WORD_REQUEST.CONFIRM_NEW_PASSWORD.NOT_BLANK")
    @Size(min = 8, message = "VALIDATION.CHANGE_PASS_WORD_REQUEST.CONFIRM_NEW_PASSWORD.SIZE")
    private String  confirmNewPassword;

}
