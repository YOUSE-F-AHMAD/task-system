package com.example.task_system.notes.dto;

import lombok.Data;


public record NoteDTO(
        String name,
        String description,
        Long NoteBookId
) {
}
