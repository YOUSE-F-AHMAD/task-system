package com.example.task_system.task.controller;

import com.example.task_system.task.request.TaskEditStatus;
import com.example.task_system.task.request.TaskRequest;
import com.example.task_system.task.response.TaskDtoResponse;
import com.example.task_system.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/api/v1/notebooks/{notebook_id}/tasks")
    public ResponseEntity<TaskDtoResponse> createTask(
            @PathVariable("notebook_id") Long notebookId,
            @RequestBody TaskRequest taskRequest
    )
    {
       return ResponseEntity.ok(this.taskService.createTask(notebookId,taskRequest));
    }

    @GetMapping("/api/v1/notebooks/{notebook_id}/tasks")
    public ResponseEntity<Page<TaskDtoResponse>> getTasks(
            @PathVariable("notebook_id") Long notebookId,
            @RequestParam("number") int number,
            @RequestParam("size") int size
            ){
        return ResponseEntity.ok(this.taskService.tasksByNoteBookId(notebookId,number,size));
    }

    @PutMapping("/api/v1/tasks/{task_id}")
    public ResponseEntity<TaskDtoResponse> editTask(
            @PathVariable("task_id") Long taskId,
            @RequestBody TaskRequest taskRequest,
            @AuthenticationPrincipal UserDetails userDetails
            ){
        return ResponseEntity.ok(this.taskService.taskEdit(userDetails,taskId,taskRequest));
    }

    @PatchMapping("/api/v1/tasks/{task_id}/status")
    public ResponseEntity<TaskDtoResponse> editTaskStatus(
            @PathVariable("task_id") Long taskId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody TaskEditStatus taskEditStatus
    ) {
        return ResponseEntity.ok(this.taskService.taskStatusEdit(userDetails,taskId,taskEditStatus));
    }

    @DeleteMapping("/api/v1/tasks/{task_id}")
    public ResponseEntity<String> deleteTask(
            @PathVariable("task_id") Long taskId,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        this.taskService.deleteTask(userDetails,taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
