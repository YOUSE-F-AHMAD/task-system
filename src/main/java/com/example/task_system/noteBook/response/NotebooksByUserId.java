package com.example.task_system.noteBook.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NotebooksByUserId {

    @NotNull(message = "VALIDATION.NOTEBOOKS_BY_USER_ID.NOTEBOOK_ID.NOT_NULL")
    private Long notebook_id;

    @NotBlank(message = "VALIDATION.GET_ALL_NOTEBOOK.NOTEBOOK.NAME.NOT_BLANK")
    private  String name;
}
