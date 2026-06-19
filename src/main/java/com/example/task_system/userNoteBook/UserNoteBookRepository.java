package com.example.task_system.userNoteBook;

import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserNoteBookRepository extends JpaRepository<UserNoteBook,Long> , JpaSpecificationExecutor<UserNoteBook> {
    Optional<UserNoteBook> findByUserAndNoteBook(Users user, NoteBook oldNoteBook);

    void deleteByUserAndNoteBook(Users userWillRemove, NoteBook noteBook);
}
