package com.example.task_system.task.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTask {

    @NotBlank(message = "VALIDATION.CREATE_TASK.NAME.NOT_BLANK")
    private String name;

    @NotBlank(message = "VALIDATION.CREATE_TASK.DESCRIPTION.NOT_BLANK")
    private String description;

    @NotBlank(message = "VALIDATION.CREATE_TASK.DATE_FOR_ENDED.NOT_BLANK")
    private LocalDate dateForEnded;

    @NotBlank(message = "VALIDATION.CREATE_TASK.NOTEBOOK_ID.NOT_BLANK")
    private Long noteBookId;
}
