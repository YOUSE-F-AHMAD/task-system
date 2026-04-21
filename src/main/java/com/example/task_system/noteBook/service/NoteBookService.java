package com.example.task_system.noteBook.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import com.example.task_system.noteBook.requiste.ChangeNameOfNoteBook;
import com.example.task_system.noteBook.response.GetAllNoteBook;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@RequiredArgsConstructor
@Service
public class NoteBookService {

    private final NoteBookRepository repository;

    public NoteBook createNewNoteBook(
            String name
            )
    {
        if (name == null){
            throw new BusinessException(ErrorCode.FIELD_NAME_SHOULD_HAS_VALUE);
        }
        final NoteBook noteBook = NoteBook.builder()
                .name(name)
                .build();
        return this.repository.save(noteBook);
    }

    public List<GetAllNoteBook> getNoteBookList(){
        final List<NoteBook> noteBooks = this.repository.findAll();
        return  toDtoNoteBook(noteBooks);
    }

    private List<GetAllNoteBook> toDtoNoteBook(List<NoteBook> noteBooks) {
        return noteBooks.stream()
                .map(noteBook -> new GetAllNoteBook(noteBook.getName()))
                .toList();
    }

    public void changeNoteBookName(ChangeNameOfNoteBook change){
        final NoteBook oldNoteBook = this.repository.findByName(change.getOldName())
                .orElseThrow();
        oldNoteBook.setName(change.getNewName());
        ResponseEntity.ok(this.repository.save(oldNoteBook));
    }


    public void removeNoteBookById(Long id){
        if (this.repository.existsById(id)){
            this.repository.deleteById(id);
        }
    }
}
