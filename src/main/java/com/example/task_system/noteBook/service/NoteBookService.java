package com.example.task_system.noteBook.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.mapper.NotebookMapper;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import com.example.task_system.noteBook.repository.SpecificationQueryNoteBook;
import com.example.task_system.noteBook.request.ChangeNameOfNoteBook;
import com.example.task_system.noteBook.request.CreateNoteBookRequest;
import com.example.task_system.noteBook.request.SharNoteBookRequest;
import com.example.task_system.noteBook.response.FriendInfoResponse;
import com.example.task_system.noteBook.response.NoteBookDto;
import com.example.task_system.noteBook.response.NotebooksByUserId;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepository;
import com.example.task_system.userNoteBook.NoteBookRole;
import com.example.task_system.userNoteBook.UserNoteBook;
import com.example.task_system.userNoteBook.UserNoteBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class NoteBookService {

    private final NoteBookRepository repository;

    private final UserRepository userRepository;

    private final NotebookMapper notebookMapper;

    private final UserNoteBookRepository userNoteBookRepository;

    @Transactional
    public NoteBook createNewNoteBook(
            UserDetails userDetails,
            CreateNoteBookRequest request)
    {
        if (request.getName() == null){
            throw new BusinessException(ErrorCode.FIELD_NAME_SHOULD_HAS_VALUE);
        }

        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userDetails.getUsername()));

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

    @Transactional
    public FriendInfoResponse shareNoteBook(Long noteBookId, UserDetails userDetails, SharNoteBookRequest request)
    {
        final String userEmail = userDetails.getUsername();
        final Users currentUser = this.userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userEmail));

        final Users user = this.userRepository.findByIdentifierOrEmail(
                request.getIdentifier()
                        ,request.getIdentifier() )
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userEmail));

        final NoteBook noteBook = this.repository.findById(noteBookId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION));

        if (!noteBook.getCreatedBy().equals(currentUser.getId()))
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        final UserNoteBook userNoteBook = UserNoteBook.builder()
                .user(user)
                .noteBook(noteBook)
                .role(NoteBookRole.USER)
                .build();
        this.userNoteBookRepository.save(userNoteBook);

        return FriendInfoResponse.builder()
                .noteBookName(noteBook.getName())
                .friendUserName(user.getUsername())
                .createdAt(userNoteBook.getCreatedDate())
                .build();
    }

    @Transactional
    public List<NotebooksByUserId> notebooksByUserId(UserDetails userDetails)
    {
        final String userEmail = userDetails.getUsername();
        final Users user = this.userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));
        final Long id = user.getId();
        Specification<UserNoteBook> spec = SpecificationQueryNoteBook.userNoteBookSpecByUserId(id);
        final List<UserNoteBook> userNoteBooks = this.userNoteBookRepository.findAll(spec);
        return  notebookMapper.toDtoNoteBooks(userNoteBooks);
    }


    @Transactional
    public NoteBookDto changeNoteBookName(
            UserDetails userDetails ,
            Long notebookId ,
            ChangeNameOfNoteBook change)
    {
        final NoteBook noteBook = this.repository.findById(notebookId)
                .orElseThrow( ()-> new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION));
        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));
        final UserNoteBook userNoteBook = this.userNoteBookRepository.findByUserAndNoteBook(user, noteBook)
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED));

        if (!userNoteBook.getRole().equals(NoteBookRole.OWNER))
            throw new BusinessException(ErrorCode.UNAUTHORIZED);

        noteBook.setName(change.getNewName());
        this.repository.save(noteBook);
        return  notebookMapper.toDtoNoteBook(noteBook);
    }

    @Transactional
    public void removeNoteBookById(UserDetails userDetails,Long notebookId)
    {

        final String userEmail = userDetails.getUsername();
        final Users user = this.userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

        final NoteBook noteBook = this.repository.findById(notebookId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION));

        final UserNoteBook userNoteBook = this.userNoteBookRepository.findByUserAndNoteBook(user,noteBook)
                .orElseThrow(()-> new BusinessException(ErrorCode.UNAUTHORIZED));

        if (!userNoteBook.getRole().equals(NoteBookRole.OWNER))
            throw new BusinessException(ErrorCode.UNAUTHORIZED);

        this.repository.deleteById(notebookId);
    }

    public void removeMemberFromNoteBook(
            UserDetails userDetails,
            Long notebookId,
            String identifier
        )
    {

        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

        final NoteBook noteBook = this.repository.findById(notebookId)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION));

        final UserNoteBook userNoteBook = this.userNoteBookRepository.findByUserAndNoteBook(user,noteBook)
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED));

        if (!(userNoteBook.getRole().equals(NoteBookRole.OWNER)))
            throw new BusinessException(ErrorCode.UNAUTHORIZED);

        final Users userWillRemove = this.userRepository.findByEmailIgnoreCase(identifier)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

        this.userNoteBookRepository.deleteByUserAndNoteBook(userWillRemove,noteBook);
    }
}
