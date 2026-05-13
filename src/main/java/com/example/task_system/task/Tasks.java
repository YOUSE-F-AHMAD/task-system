package com.example.task_system.task;

import com.example.task_system.entity.BaseEntity;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.notes.Notes;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Tasks extends BaseEntity {

    private String name;

    private LocalDate dateForEnded;

    private boolean isCompleted;

    private String description;

    @ManyToOne(
            cascade = {CascadeType.PERSIST}
    )
    @JoinColumn(name = "note_book_id")
    private NoteBook noteBook;

    @OneToMany(mappedBy = "task")
    private List<Notes> note;


}
