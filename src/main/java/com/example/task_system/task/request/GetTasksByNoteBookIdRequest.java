package com.example.task_system.task.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTasksByNoteBookIdRequest {
    @NotBlank(message = "VALIDATION.GET_TASKS_BY_NOTEBOOK_ID_REQUEST.NOTEBOOK_ID.NOT_BLANK")
    private Long noteBookId;
    @NotBlank(message = "VALIDATION.GET_TASKS_BY_NOTEBOOK_ID_REQUEST.SIZE.NOT_BLANK")
    private int size;
    @NotBlank(message = "VALIDATION.GET_TASKS_BY_NOTEBOOK_ID_REQUEST.NUMBER.NOT_BLANK")
    private int number;
}
