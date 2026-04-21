package com.example.task_system.noteBook;

import com.example.task_system.entity.BaseEntity;
import com.example.task_system.notes.Notes;
import com.example.task_system.task.Tasks;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class NoteBook extends BaseEntity {

    private String name;

    @OneToMany(
            mappedBy = "noteBook",
            fetch = FetchType.LAZY
    )
    private List<Tasks> task;


    @OneToMany(
            mappedBy = "note_book",
            fetch = FetchType.LAZY
    )
    private List<Notes> notes;

}
