package com.example.task_system.task.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.noteBook.NoteBook;
import com.example.task_system.noteBook.repository.NoteBookRepository;
import com.example.task_system.task.Tasks;
import com.example.task_system.task.repository.SpecificationQuery;
import com.example.task_system.task.repository.TaskRepository;
import com.example.task_system.task.requiste.CreateTask;
import com.example.task_system.task.response.GetAllTask;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    private final NoteBookRepository noteBookRepository;

    public Tasks createTask(
            CreateTask createTask
    )
    {
        final NoteBook noteBook = this.noteBookRepository.findById(createTask.getNoteBookId())
                .orElseThrow( ()-> new BusinessException(
                        ErrorCode.NOTEBOOK_NOT_FOUND_EXCEPTION,
                       createTask.getNoteBookId())
                );

       final Tasks tasks = Tasks.builder()
               .name(createTask.getName())
               .description(createTask.getDescription())
               .dateForEnded(createTask.getDateForEnded())
               .isCompleted(false)
               .noteBook(noteBook)
               .build();
      return this.repository.save(tasks);
    }

    // the int page and the size get value from front end
    public Page<GetAllTask> getAllTasks(byte i){
        int page = 0;
        int size = 2;
        boolean is = i == 1;
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());
        Specification<Tasks> spec = SpecificationQuery.filter(is);
        final Page<Tasks> tasks =  repository.findAll(spec,pageable);

        return toDtoTasks(tasks);
    }

    private Page<GetAllTask> toDtoTasks(Page<Tasks> tasks) {

        return tasks.map(tasks1 -> new GetAllTask(tasks1.getName(),
                                tasks1.getDescription(),
                                tasks1.getDateForEnded(),
                                tasks1.isCompleted(),
                                tasks1.getNoteBook() != null ? tasks1.getNoteBook().getId() : null));
    }
}
