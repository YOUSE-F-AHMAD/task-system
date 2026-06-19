package com.example.task_system.noteBook.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendInfoResponse{

    @NotBlank(message = "VALIDATION.FRIEND_INFO.FRIEND_USERNAME.NOT_BLANK")
    private String friendUserName;

    @NotBlank(message = "VALIDATION.FRIEND_INFO.NOTEBOOK_NAME.NOT_BLANK")
    private String noteBookName;

    @NotNull(message = "VALIDATION.FRIEND_INFO.CREATED_AT.NOT_NULL")
    private LocalDateTime createdAt;
}
