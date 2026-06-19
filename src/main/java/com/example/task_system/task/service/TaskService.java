package com.example.task_system.task.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import com.example.task_system.task.Tasks;
import com.example.task_system.task.mapper.TaskMapper;
import com.example.task_system.task.repository.SpecificationQuery;
import com.example.task_system.task.repository.TaskRepository;
import com.example.task_system.task.request.TaskEditStatus;
import com.example.task_system.task.request.TaskRequest;
import com.example.task_system.task.response.TaskDtoResponse;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    private final UserRepository userRepository;

    private final NoteBookRepository noteBookRepository;

    private final TaskMapper taskMapper;

    public TaskDtoResponse createTask(
            Long notebookId,
            TaskRequest taskRequest)
    {
        if (notebookId == null)
            throw new BusinessException(ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION);

        final NoteBook noteBook = this.noteBookRepository.findById(notebookId)
                .orElseThrow( ()-> new BusinessException(
                        ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION));

       final Tasks task = Tasks.builder()
               .title(taskRequest.getTitle())
               .content(taskRequest.getContent())
               .dueDate(taskRequest.getDueDate())
               .status(taskRequest.getStatus())
               .noteBook(noteBook)
               .build();
       this.repository.save(task);
       return taskMapper.toTaskDto(task);
    }


    public Page<TaskDtoResponse> tasksByNoteBookId(
            Long notebookId,
            Integer number,
            Integer size)
    {
        Pageable pageable = PageRequest.of(number,size, Sort.by("id").descending());
        Specification<Tasks> spec = SpecificationQuery.filter(notebookId);
        final Page<Tasks> tasks =  repository.findAll(spec,pageable);
        return taskMapper.toTaskDtoResponse(tasks);
    }

    public TaskDtoResponse taskEdit(
            UserDetails userDetails,
            Long taskId,
            TaskRequest taskRequest
            ) {
        if (userDetails == null) throw new BusinessException(ErrorCode.UNAUTHORIZED);
        if (taskId == null) throw new BusinessException(ErrorCode.ERROR_YOUR_PATH_NOT_CORRECT);

        final Tasks task = repository.findById(taskId)
                .orElseThrow(()-> new BusinessException(ErrorCode.TASK_NOT_FOUND_EXCEPTION));
        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

        if ( !(user.getId().equals(task.getCreatedBy())) )
            throw new BusinessException(ErrorCode.UNAUTHORIZED);

        task.setTitle(taskRequest.getTitle());
        task.setContent(taskRequest.getContent());
        task.setDueDate(taskRequest.getDueDate());
        task.setStatus(taskRequest.getStatus());

        this.repository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public TaskDtoResponse taskStatusEdit(
            UserDetails userDetails,
            Long taskId,
            TaskEditStatus taskEditStatus
    ){
        if (taskId == null) throw new BusinessException(ErrorCode.ERROR_YOUR_PATH_NOT_CORRECT);
        if (userDetails == null) throw new BusinessException(ErrorCode.UNAUTHORIZED);

        final Tasks task = this.repository.findById(taskId)
                .orElseThrow(()-> new BusinessException(ErrorCode.TASK_NOT_FOUND_EXCEPTION));

        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow( ()-> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

        if ( !(user.getId().equals(task.getCreatedBy())) ) throw new BusinessException(ErrorCode.UNAUTHORIZED);

        task.setStatus(taskEditStatus.getStatus());
        this.repository.save(task);
        return taskMapper.toTaskDto(task);
    }

    public void deleteTask(UserDetails userDetails, Long taskId) {
        if (taskId == null)
            throw new BusinessException(ErrorCode.ERROR_YOUR_PATH_NOT_CORRECT);
        if (userDetails == null)
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        final Users user = this.userRepository.findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION));

        final Tasks task = this.repository.findById(taskId)
                .orElseThrow(()-> new BusinessException(ErrorCode.TASK_NOT_FOUND_EXCEPTION));

        if ( !(user.getId().equals(task.getCreatedBy())) ) throw new BusinessException(ErrorCode.UNAUTHORIZED);
        this.repository.delete(task);
    }
}
