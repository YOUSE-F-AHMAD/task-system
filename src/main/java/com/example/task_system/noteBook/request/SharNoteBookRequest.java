package com.example.task_system.noteBook.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SharNoteBookRequest {

    @NotBlank(message = "VALIDATION.SHAR_NOTEBOOK_REQUEST.NOTEBOOK.ID.NOT_BLANK")
    private Long noteBookId;

    @NotBlank(message = "VALIDATION.SHAR_NOTEBOOK_REQUEST.USERID.NOT_BLANK")
    private Long userId;

}
