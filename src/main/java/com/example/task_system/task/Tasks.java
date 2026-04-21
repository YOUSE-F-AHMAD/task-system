package com.example.task_system.task;

import com.example.task_system.entity.BaseEntity;
import com.example.task_system.noteBook.NoteBook;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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


}
