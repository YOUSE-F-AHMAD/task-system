package com.example.task_system.notes.controller;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.notes.Notes;
import com.example.task_system.notes.dto.NoteDTO;
import com.example.task_system.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotesController {

    private final NoteService noteService;

    @PostMapping("/createNote")
    public void createNote(
            @RequestBody NoteDTO noteDTO
            )
    {
        final Notes note = this.noteService.createNote(noteDTO);
        if (note == null) throw new BusinessException(
                ErrorCode.NOTE_NOT_FOUND_EXCEPTION);
        else ResponseEntity.status(HttpStatus.CREATED);
    }
}
