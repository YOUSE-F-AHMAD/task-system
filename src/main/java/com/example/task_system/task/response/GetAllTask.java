package com.example.task_system.task.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllTask {

    @NotBlank(message = "VALIDATION.GET_ALL_TASK.NAME.NOT_BLANK")
    private String name;

    @NotBlank(message = "VALIDATION.GET_ALL_TASK.DESCRIPTION.NOT_BLANK")
    private String description;

    @NotBlank(message = "VALIDATION.GET_ALL_TASK.DATE_FOR_ENDED.NOT_BLANK")
    private LocalDate dateForEnded;

    @NotBlank(message = "VALIDATION.GET_ALL_TASK.IS_COMPLETED.NOT_BLANK")
    private boolean isCompleted;

    @NotBlank(message = "VALIDATION.GET_ALL_TASK.NOTEBOOK_ID.NOT_BLANK")
    private Long noteBookId;

}
