package com.example.task_system.notes.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import com.example.task_system.notes.Notes;
import com.example.task_system.notes.mapper.NoteMapper;
import com.example.task_system.notes.repository.NotesRepository;
import com.example.task_system.notes.repository.SpecificationQueryNotes;
import com.example.task_system.notes.request.NotesRequest;
import com.example.task_system.notes.response.NotesDtoResponse;
import com.example.task_system.task.Tasks;
import com.example.task_system.task.repository.TaskRepository;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepository;
import com.example.task_system.userNoteBook.UserNoteBook;
import com.example.task_system.userNoteBook.UserNoteBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NotesRepository notesRepository;

    private final UserRepository userRepository;

    private final NoteBookRepository noteBookRepository;

    private final UserNoteBookRepository userNoteBookRepository;

//    private final NotesMapper mapperStruct;

    private final NoteMapper noteMapper;

    private final TaskRepository taskRepository;

    public NotesDtoResponse createNote(
            UserDetails userDetails,
            Long taskId,
            NotesRequest request
    )
    {
        final Tasks task = this.taskRepository.findById(taskId)
                .orElseThrow( ()-> new BusinessException(ErrorCode.NOTE_NOT_FOUND_EXCEPTION)
                );
        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(()-> new BusinessException(ErrorCode.UNAUTHORIZED));

        final NoteBook noteBook = this.noteBookRepository.findById(task.getNoteBook().getId())
                .orElseThrow(()-> new BusinessException(ErrorCode.NOTE_NOT_FOUND_EXCEPTION));
        final UserNoteBook userNoteBook = this.userNoteBookRepository.findByUserAndNoteBook(user,noteBook)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOTE_NOT_FOUND_EXCEPTION));

        final Notes note = Notes.builder()
                .name(request.name())
                .description(request.description())
                .task(task)
                .build();
        this.notesRepository.save(note);
        return noteMapper.toNoteDto(note);
    }

    public Page<NotesDtoResponse> NotesByTaskId(Long taskId,Integer number,Integer size )
    {
        if ( size < 0 || number < 0) throw new BusinessException(ErrorCode.ERROR_YOUR_PATH_NOT_CORRECT);

        Pageable pageable = PageRequest.of(number,size,Sort.by("createdDate").descending());

        Specification<Notes> spec = SpecificationQueryNotes.filter(taskId);

        Page<Notes> notes = this.notesRepository.findAll(spec,pageable);

        return noteMapper.toDtoNotes(notes);
    }


    public NotesDtoResponse putNote(UserDetails userDetails, Long noteId,NotesRequest request) {
        if (noteId == null)
            throw new BusinessException(ErrorCode.ERROR_YOUR_PATH_NOT_CORRECT);
        final Notes note = this.notesRepository.findById(noteId)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOTE_NOT_FOUND_EXCEPTION));
        if (userDetails == null)
            throw new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(()-> new BusinessException(ErrorCode.UNAUTHORIZED));
        if ( !(user.getId().equals(note.getCreatedBy()) ) )
            throw new BusinessException(ErrorCode.UNAUTHORIZED);

        note.setName(request.name());
        note.setDescription(request.description());

        this.notesRepository.save(note);
        return noteMapper.toNoteDto(note);
    }

    public void deleteNote(UserDetails userDetails, Long noteId) {
        if (noteId == null)
            throw new BusinessException(ErrorCode.ERROR_YOUR_PATH_NOT_CORRECT);
        final Notes note = this.notesRepository.findById(noteId)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOTE_NOT_FOUND_EXCEPTION));
        if (userDetails == null)
            throw new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION);
        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(()-> new BusinessException(ErrorCode.UNAUTHORIZED));
        if ( !(user.getId().equals(note.getCreatedBy()) ) )
            throw new BusinessException(ErrorCode.UNAUTHORIZED);

        this.notesRepository.delete(note);
    }
}
