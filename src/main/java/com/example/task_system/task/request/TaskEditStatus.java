package com.example.task_system.task.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEditStatus {

    @NotBlank(message = "VALIDATION.TASK_EDIT_STATUS.STATUS.NOT_BLANK")
    private String status;
}
