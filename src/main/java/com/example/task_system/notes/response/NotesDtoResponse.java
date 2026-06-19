package com.example.task_system.notes.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesDtoResponse{

        @NotBlank(message = "VALIDATION.NOTE_DTO.NAME.NOT_BLANK")
        private String name;

        @NotBlank(message = "VALIDATION.NOTE_DTO.DESCRIPTION.NOT_BLANK")
        private String description;

        @NotNull(message = "VALIDATION.NOTE_DTO.CREATE_AT.NOT_NULL")
        private LocalDateTime createAt;

        @NotNull(message = "VALIDATION.NOTE_DTO.LAST_MODIFIED_DATE.NOT_NULL")
        private LocalDateTime lastModifiedDate;

        @NotNull(message = "VALIDATION.NOTE_DTO.TASK_ID.NOT_NULL")
        private Long taskId;
}
