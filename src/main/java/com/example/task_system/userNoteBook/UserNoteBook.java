package com.example.task_system.userNoteBook;

import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.user.Users;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class UserNoteBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "noteBook_id")
    private NoteBook noteBook;

    @Enumerated(EnumType.STRING)
    private NoteBookRole role;
}
