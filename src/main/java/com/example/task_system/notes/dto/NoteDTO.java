package com.example.task_system.notes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public record NoteDTO(

        @NotBlank(message = "VALIDATION.NOTE_DTO.NAME.NOT_BLANK")
        String name,
        @NotBlank(message = "VALIDATION.NOTE_DTO.DESCRIPTION.NOT_BLANK")
        String description,
        @NotBlank(message = "VALIDATION.NOTE_DTO.TASK_ID.NOT_BLANK")
        Long taskId
) {
}
