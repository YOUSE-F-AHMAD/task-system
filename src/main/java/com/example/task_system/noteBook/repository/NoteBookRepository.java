package com.example.task_system.noteBook.repository;

import com.example.task_system.noteBook.NoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NoteBookRepository extends JpaRepository<NoteBook,Long> , JpaSpecificationExecutor<NoteBook> {

    Optional<NoteBook> findByName(String name);

}
