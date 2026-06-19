package com.example.task_system.notes.mapper;


import com.example.task_system.notes.Notes;
import com.example.task_system.notes.response.NotesDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {


    public NotesDtoResponse toNoteDto(Notes note) {
        return NotesDtoResponse.builder()
                .name(note.getName())
                .description(note.getDescription())
                .createAt(note.getCreatedDate())
                .lastModifiedDate(note.getLastModifiedDate())
                .taskId(note.getTask() != null ? note.getTask().getId() : null)
                .build();
    }

    public Page<NotesDtoResponse> toDtoNotes(Page<Notes> notes) {
        return notes.map( notes1 -> new NotesDtoResponse(
                notes1.getName(),
                notes1.getDescription(),
                notes1.getCreatedDate(),
                notes1.getLastModifiedDate(),
                notes1.getTask() != null ? notes1.getTask().getId() : null
        ));
    }

}