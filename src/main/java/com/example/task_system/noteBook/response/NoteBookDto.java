package com.example.task_system.noteBook.response;

import lombok.extern.java.Log;

public record NoteBookDto(
        String name,
        Long notebookId
) {
}
