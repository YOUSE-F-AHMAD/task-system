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
public class CreateNoteBookRequest {

    @NotBlank(message = "VALIDATION.CREATE_NOTEBOOK_REQUEST.USERID.NOT_BLANK")
    private Long userId;

    @NotBlank(message = "VALIDATION.CREATE_NOTEBOOK_REQUEST.NOTEBOOK.NAME.NOT_BLANK")
    private String name;

}
