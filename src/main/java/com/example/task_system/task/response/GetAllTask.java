package com.example.task_system.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllTask {

    private String name;

    private String description;

    private LocalDate dateForEnded;

    private boolean isCompleted;

    private Long noteBookId;

}
