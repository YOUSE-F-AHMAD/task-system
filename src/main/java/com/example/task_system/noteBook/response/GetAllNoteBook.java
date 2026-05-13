package com.example.task_system.noteBook.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetAllNoteBook {

    @NotBlank(message = "VALIDATION.GET_ALL_NOTEBOOK.NOTEBOOK.NAME.NOT_BLANK")
    private  String name;
}
