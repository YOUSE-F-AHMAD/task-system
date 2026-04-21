package com.example.task_system.noteBook.controller;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.requiste.ChangeNameOfNoteBook;
import com.example.task_system.noteBook.requiste.CreateNoteBookRequest;
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

    @PostMapping("/create-note-book")
    public void createNewNoteBook(
            @RequestBody CreateNoteBookRequest request
            )
    {

        final NoteBook noteBook_1 = this.noteBookService.createNewNoteBook(request.getName());

        if (noteBook_1 == null) throw new BusinessException(
                ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION);

        else ResponseEntity.status(HttpStatus.CREATED);
    }

    @PostMapping("/changeNameOfNoteBook")
    public ResponseEntity<Void> changeNoteBookName(
            @RequestBody ChangeNameOfNoteBook change
            )
    {
        this.noteBookService.changeNoteBookName(change);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getAllNoteBooks")
    public List<GetAllNoteBook> getAllNoteBooks()
    {
        return this.noteBookService.getNoteBookList();
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
