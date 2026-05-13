package com.example.task_system.notes.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.notes.Notes;
import com.example.task_system.notes.dto.NoteDTO;
import com.example.task_system.notes.repository.NotesRepository;
import com.example.task_system.notes.repository.SpecificationQueryNotes;
import com.example.task_system.notes.request.GetNoteByTaskIdRequest;
import com.example.task_system.task.Tasks;
import com.example.task_system.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NotesRepository notesRepository;

    private final TaskRepository taskRepository;

    public Notes createNote(
        NoteDTO noteDTO
    )
    {
        final Tasks task = this.taskRepository.findById(noteDTO.taskId())
                .orElseThrow( ()-> new BusinessException(ErrorCode.NOTE_NOT_FOUND_EXCEPTION)
                );
        final Notes note = Notes.builder()
                .name(noteDTO.name())
                .description(noteDTO.description())
                .task(task)
                .build();
        return this.notesRepository.save(note);
    }

    public Page<NoteDTO> getNotesByTaskId(GetNoteByTaskIdRequest request){
        Pageable pageable = PageRequest.of(request.getNumber(), request.getSize()
                , Sort.by("lastModifiedDate").descending());

        Specification<Notes> spec = SpecificationQueryNotes.filter(request.getNoteId());

        Page<Notes> notes = this.notesRepository.findAll(spec,pageable);

        return toDtoNote(notes);
    }

    private Page<NoteDTO> toDtoNote(Page<Notes> notes) {
        return notes.map( notes1 -> new NoteDTO(notes1.getName(),
                notes1.getDescription(),
                notes1.getTask() != null ? notes1.getTask().getId() : null));
    }
}
