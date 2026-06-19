package com.example.task_system.notes.request;

import jakarta.validation.constraints.NotBlank;


public record NotesRequest(

        @NotBlank(message = "VALIDATION.NOTE_DTO.NAME.NOT_BLANK")
        String name,
        @NotBlank(message = "VALIDATION.NOTE_DTO.DESCRIPTION.NOT_BLANK")
        String description
) {
}
