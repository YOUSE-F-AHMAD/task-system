package com.example.task_system.task.mapper;

import com.example.task_system.task.Tasks;
import com.example.task_system.task.response.TaskDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Page<TaskDtoResponse> toTaskDtoResponse(Page<Tasks> tasks) {

        return tasks.map(tasks1 -> new TaskDtoResponse(
                tasks1.getTitle(),
                tasks1.getContent(),
                tasks1.getDueDate(),
                tasks1.getStatus(),
                tasks1.getCreatedBy(),
                tasks1.getLastModifiedBy(),
                tasks1.getCreatedDate(),
                tasks1.getLastModifiedDate(),
                tasks1.getNoteBook() != null ? tasks1.getNoteBook().getId() : null));
    }

    public TaskDtoResponse toTaskDto(Tasks tasks){
        return new TaskDtoResponse(
                tasks.getTitle(),
                tasks.getContent(),
                tasks.getDueDate(),
                tasks.getStatus(),
                tasks.getCreatedBy(),
                tasks.getLastModifiedBy(),
                tasks.getCreatedDate(),
                tasks.getLastModifiedDate(),
                tasks.getNoteBook() != null ? tasks.getNoteBook().getId() : null);
    }
}
