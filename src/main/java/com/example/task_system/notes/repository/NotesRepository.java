package com.example.task_system.notes.repository;

import com.example.task_system.notes.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes,Long> {
}
