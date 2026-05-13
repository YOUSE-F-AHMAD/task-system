package com.example.task_system.task.controller;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.task.Tasks;
import com.example.task_system.task.request.CreateTask;
import com.example.task_system.task.request.GetTasksByNoteBookIdRequest;
import com.example.task_system.task.response.GetAllTask;
import com.example.task_system.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createTask")
    public void createTask(
            @RequestBody CreateTask createTask
            )
    {
       final Tasks task = this.taskService.createTask(createTask);
       if (task == null) throw new BusinessException(
               ErrorCode.TASK_NOT_FOUND_EXCEPTION

       );
       else ResponseEntity.status(HttpStatus.CREATED);
    }

    @GetMapping("/getTaskByNoteBookId")
    public ResponseEntity<Page<GetAllTask>> getTasks(
            @RequestBody GetTasksByNoteBookIdRequest request
            ){
        return ResponseEntity.ok(this.taskService.getAllTasksByNoteBookId(request));
    }
}
