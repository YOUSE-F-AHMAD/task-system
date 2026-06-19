package com.example.task_system.task.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDtoResponse {

    @NotBlank(message = "VALIDATION.TASK_DTO_RESPONSE.TITLE.NOT_BLANK")
    private String title;

    @NotBlank(message = "VALIDATION.TASK_DTO_RESPONSE.CONTENT.NOT_BLANK")
    private String content;

    @NotNull(message = "VALIDATION.TASK_DTO_RESPONSE.DUE_DATE.NOT_NULL")
    private LocalDate dueDate;

    @NotBlank(message = "VALIDATION.TASK_DTO_RESPONSE.STATUS.NOT_BLANK")
    private String status;

    @NotNull(message = "VALIDATION.TASK_DTO_RESPONSE.CREATED_BY.NOT_NULL")
    private Long createdBy;

    @NotNull(message = "VALIDATION.TASK_DTO_RESPONSE.LAST_MODIFIED_BY.NOT_NULL")
    private Long lastModifiedBy;

    @NotNull(message = "VALIDATION.TASK_DTO_RESPONSE.CREATED_DATE.NOT_NULL")
    private LocalDateTime createdDate;

    @NotNull(message = "VALIDATION.TASK_DTO_RESPONSE.LAST_MODIFIED_DATE.NOT_NULL")
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "VALIDATION.TASK_DTO_RESPONSE.NOTEBOOK_ID.NOT_NULL")
    private Long noteBookId;

}
