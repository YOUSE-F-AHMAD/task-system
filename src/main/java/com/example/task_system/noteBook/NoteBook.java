package com.example.task_system.noteBook;

import com.example.task_system.entity.BaseEntity;
import com.example.task_system.task.Tasks;
import com.example.task_system.userNoteBook.UserNoteBook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Tasks> task;

    @OneToMany(mappedBy = "noteBook" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserNoteBook> userNoteBooks;

}
