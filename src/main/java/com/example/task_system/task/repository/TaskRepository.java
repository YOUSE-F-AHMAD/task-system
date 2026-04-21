package com.example.task_system.task.repository;

import com.example.task_system.task.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Tasks,Long>, JpaSpecificationExecutor<Tasks> {

}
