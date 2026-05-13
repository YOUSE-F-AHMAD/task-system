package com.example.task_system.notes.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNoteByTaskIdRequest {

    @NotBlank(message = "VALIDATION.GET_NOTE_BY_TASK_ID_REQUEST.NOTE.ID.NOT_BLANK")
    private Long noteId;

    @NotBlank(message = "VALIDATION.GET_NOTE_BY_TASK_ID_REQUEST.SIZE.NOT_BLANK")
    private int size;

    @NotBlank(message = "VALIDATION.GET_NOTE_BY_TASK_ID_REQUEST.FIRSTNAME.NOT_BLANK")
    private int number;
}
