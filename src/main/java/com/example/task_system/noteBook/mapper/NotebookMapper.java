package com.example.task_system.noteBook.mapper;

import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.response.NoteBookDto;
import com.example.task_system.noteBook.response.NotebooksByUserId;
import com.example.task_system.userNoteBook.UserNoteBook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotebookMapper {


    public List<NotebooksByUserId> toDtoNoteBooks(List<UserNoteBook> userNoteBooks)
    {

        return userNoteBooks.stream()
                .map(noteBook ->
                        new NotebooksByUserId(noteBook.getNoteBook().getId(),noteBook.getNoteBook().getName()))
                .toList();
    }

    public NoteBookDto toDtoNoteBook(NoteBook oldNoteBook) {
        return new NoteBookDto(oldNoteBook.getName(),oldNoteBook.getId());
    }
}
