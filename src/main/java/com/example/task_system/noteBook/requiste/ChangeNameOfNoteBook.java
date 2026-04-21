package com.example.task_system.noteBook.requiste;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeNameOfNoteBook {

    private String oldName;

    private String newName;
}
