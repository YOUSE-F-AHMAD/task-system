package com.example.task_system.notes.controller;

import com.example.task_system.notes.request.NotesRequest;
import com.example.task_system.notes.response.NotesDtoResponse;
import com.example.task_system.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotesController {

    private final NoteService noteService;

    @PostMapping("/api/v1/tasks/{task_id}/notes")
    public ResponseEntity<NotesDtoResponse> createNote(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("task_id") Long taskId,
            @RequestBody NotesRequest request
    ) {

        return ResponseEntity.ok(this.noteService.createNote(userDetails,taskId,request));
    }

    @GetMapping("/api/v1/tasks/{task_id}/notes")
    public ResponseEntity<Page<NotesDtoResponse>> getNoteByTaskId(
            @PathVariable("task_id") Long taskId,
            @RequestParam("number") int number,
            @RequestParam("size") int size
    )
    {
     return ResponseEntity.ok(this.noteService.NotesByTaskId(taskId,number,size));
    }

    @PutMapping("/api/v1/notes/{note_id}")
    public ResponseEntity<NotesDtoResponse> putNote(
            @PathVariable("note_id") Long noteId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody NotesRequest request
            ) {
        return ResponseEntity.ok(this.noteService.putNote(userDetails,noteId,request));
    }
    @DeleteMapping("/api/v1/notes/{note_id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable("note_id") Long noteId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        this.noteService.deleteNote(userDetails,noteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
