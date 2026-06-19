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
public class ChangeNameOfNoteBook {

    @NotBlank(message = "VALIDATION.CHANGE_NAME_OF_NOTEBOOK.NOTEBOOK.NewNAME.NOT_BLANK")
    private String newName;
}
