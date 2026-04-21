package com.example.task_system.notes;

import com.example.task_system.entity.BaseEntity;
import com.example.task_system.noteBook.NoteBook;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Notes extends BaseEntity {

    private String name;

    private String description;

    @ManyToOne(
            cascade = {CascadeType.MERGE,CascadeType.PERSIST}
    )
    @JoinColumn(name = "note_book_id")
    private NoteBook note_book;

}
