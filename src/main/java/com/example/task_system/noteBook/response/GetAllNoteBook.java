package com.example.task_system.noteBook.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetAllNoteBook {

    private  String name;
}
