package com.example.task_system.userNoteBook;

import com.example.task_system.noteBook.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserNoteBookRepository extends JpaRepository<UserNoteBook,Long> , JpaSpecificationExecutor<UserNoteBook> {
}
