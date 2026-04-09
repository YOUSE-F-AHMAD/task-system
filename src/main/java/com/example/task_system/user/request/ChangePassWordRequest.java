package com.example.task_system.user.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePassWordRequest {

    private String currentPassword;

    private String newPassword;

    private String  confirmNewPassword;

}
