package com.example.task_system.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    @NotBlank(message = "VALIDATION.CREATE_TASK.TITLE.NOT_BLANK")
    private String title;

    @NotBlank(message = "VALIDATION.CREATE_TASK.CONTENT.NOT_BLANK")
    private String content;

    @NotBlank(message = "VALIDATION.CREATE_TASK.STATUS.NOT_BLANK")
    private String status;

    @NotNull(message = "VALIDATION.CREATE_TASK.DUE_DATE.NOT_NULL")
    private LocalDate dueDate;

}
