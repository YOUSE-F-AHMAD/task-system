package com.example.task_system.notes.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.notes.Notes;
import com.example.task_system.notes.dto.NoteDTO;
import com.example.task_system.notes.repository.NotesRepository;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NotesRepository notesRepository;

    private final NoteBookRepository noteBookRepository;

    public Notes createNote(
        NoteDTO noteDTO
    )
    {
        final NoteBook noteBook = this.noteBookRepository.findById(noteDTO.NoteBookId())
                .orElseThrow( ()-> new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION,
                       noteDTO.NoteBookId())
                );
        final Notes note = Notes.builder()
                .name(noteDTO.name())
                .description(noteDTO.description())
                .note_book(noteBook)
                .build();
        return this.notesRepository.save(note);
    }
}
