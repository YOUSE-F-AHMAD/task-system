package com.example.task_system.noteBook.controller;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.request.ChangeNameOfNoteBook;
import com.example.task_system.noteBook.request.CreateNoteBookRequest;
import com.example.task_system.noteBook.request.SharNoteBookRequest;
import com.example.task_system.noteBook.response.FriendInfoResponse;
import com.example.task_system.noteBook.response.NotebooksByUserId;
import com.example.task_system.noteBook.service.NoteBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteBookController {

    private final NoteBookService noteBookService;

    @PostMapping("/api/v1/notebooks")
    public ResponseEntity<NoteBook> createNewNoteBook(
            @RequestBody CreateNoteBookRequest request,
            @AuthenticationPrincipal UserDetails currentUser
    )
    {
        final NoteBook noteBook_1 = this.noteBookService.createNewNoteBook(currentUser,request);
        if (noteBook_1 == null) throw new BusinessException(
                ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION);

        else return ResponseEntity.status(HttpStatus.CREATED).body(noteBook_1);
    }

    @GetMapping("/api/v1/notebooks")
    public ResponseEntity<List<NotebooksByUserId>> getAllNoteBooks(
            @AuthenticationPrincipal UserDetails userDetails
    )
    {
        return ResponseEntity.ok(this.noteBookService.notebooksByUserId(userDetails));
    }


    @PutMapping("/api/v1/notebooks/{notebookId}")
    public ResponseEntity<NoteBook> changeNoteBookName(
            @PathVariable("notebookId") Long notebookId,
            @RequestBody ChangeNameOfNoteBook change,
            @AuthenticationPrincipal UserDetails userDetails
            )
    {
        return ResponseEntity.ok(this.noteBookService.changeNoteBookName(userDetails,notebookId,change));
    }

    @PostMapping("/api/v1/notebooks/{notebookId}/member")
    public ResponseEntity<FriendInfoResponse> shareNoteBook(
            @PathVariable("notebookId") Long notebookId,
            @RequestBody SharNoteBookRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    )
    {

        return ResponseEntity.ok(this.noteBookService
                .shareNoteBook(notebookId, userDetails,request));
    }

    @DeleteMapping("/api/v1/notebooks/{notebookId}")
    public ResponseEntity<Void> deleteNoteBook(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("notebookId") Long id
    )
    {
        this.noteBookService.removeNoteBookById(userDetails,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/api/v1/notebooks/{notebook_id}/member/{identifier}")
    public ResponseEntity<Void> notebooksDeleteMember(
            @PathVariable("notebook_id") Long notebookId,
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("identifier") String identifier
    ) {

        noteBookService.removeMemberFromNoteBook(userDetails,notebookId,identifier);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
