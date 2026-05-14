package com.example.task_system.noteBook.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import com.example.task_system.noteBook.repository.SpecificationQueryNoteBook;
import com.example.task_system.noteBook.request.ChangeNameOfNoteBook;
import com.example.task_system.noteBook.request.CreateNoteBookRequest;
import com.example.task_system.noteBook.request.SharNoteBookRequest;
import com.example.task_system.noteBook.response.GetAllNoteBook;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepository;
import com.example.task_system.userNoteBook.NoteBookRole;
import com.example.task_system.userNoteBook.UserNoteBook;
import com.example.task_system.userNoteBook.UserNoteBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class NoteBookService {

    private final NoteBookRepository repository;

    private final UserRepository userRepository;

    private final UserNoteBookRepository userNoteBookRepository;

    @Transactional
    public NoteBook createNewNoteBook(CreateNoteBookRequest request)
    {
        if (request.getName() == null){
            throw new BusinessException(ErrorCode.FIELD_NAME_SHOULD_HAS_VALUE);
        }
        final Users user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION,request.getUserId()));

        final NoteBook noteBook = NoteBook.builder()
                .name(request.getName())
                .build();
        final UserNoteBook userNoteBook = UserNoteBook.builder()
                .user(user)
                .noteBook(noteBook)
                .role(NoteBookRole.OWNER)
                .build();
        this.userNoteBookRepository.save(userNoteBook);
        return this.repository.save(noteBook);
    }

    public void sharNoteBook(SharNoteBookRequest request){
        final NoteBook noteBook = this.repository
                .findById(request.getNoteBookId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION));

        final Users user = this.userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION,request.getUserId()));

        final UserNoteBook newUserNoteBook = UserNoteBook.builder()
                .user(user)
                .noteBook(noteBook)
                .role(NoteBookRole.EDITOR)
                .build();
        this.userNoteBookRepository.save(newUserNoteBook);
    }

    public List<GetAllNoteBook> getNoteBookList(Long id){
        Specification<UserNoteBook> spec = SpecificationQueryNoteBook.filter(id);
        final List<UserNoteBook> userNoteBooks = this.userNoteBookRepository.findAll(spec);
        return  toDtoNoteBook(userNoteBooks);
    }

    private List<GetAllNoteBook> toDtoNoteBook(List<UserNoteBook> userNoteBooks) {

        return userNoteBooks.stream()
                .map(noteBook ->
                        new GetAllNoteBook(noteBook.getNoteBook().getName()))
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
