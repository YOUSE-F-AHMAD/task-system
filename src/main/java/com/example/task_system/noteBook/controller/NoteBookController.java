package com.example.task_system.noteBook.controller;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.request.ChangeNameOfNoteBook;
import com.example.task_system.noteBook.request.CreateNoteBookRequest;
import com.example.task_system.noteBook.request.SharNoteBookRequest;
import com.example.task_system.noteBook.response.GetAllNoteBook;
import com.example.task_system.noteBook.service.NoteBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteBookController {

    private final NoteBookService noteBookService;

    @PostMapping("/createNoteBook")
    public ResponseEntity<NoteBook> createNewNoteBook(
            @RequestBody CreateNoteBookRequest request )
    {
        final NoteBook noteBook_1 = this.noteBookService.createNewNoteBook(request);

        if (noteBook_1 == null) throw new BusinessException(
                ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION);

        else return ResponseEntity.status(HttpStatus.CREATED).body(noteBook_1);
    }

    @PostMapping("/sharNoteBook")
    private void sharNoteBook(
            @RequestBody SharNoteBookRequest request)
    {
        this.noteBookService.sharNoteBook(request);
    }

    @PostMapping("/changeNameOfNoteBook")
    public ResponseEntity<Void> changeNoteBookName(
            @RequestBody ChangeNameOfNoteBook change
            )
    {
        this.noteBookService.changeNoteBookName(change);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAllNoteBooksWithID/{id}")
    public List<GetAllNoteBook> getAllNoteBooks(
            @PathVariable("id") Long id
    )
    {
        return this.noteBookService.getNoteBookList(id);
    }

    @DeleteMapping("/deleteNoteBook/{id}")
    public void deleteNoteBook(
            @RequestParam Long id
    )
    {
        this.noteBookService.removeNoteBookById(id);
        ResponseEntity.status(HttpStatus.ACCEPTED);
    }


}
