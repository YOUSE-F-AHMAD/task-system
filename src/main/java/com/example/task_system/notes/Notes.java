package com.example.task_system.notes;

import com.example.task_system.entity.BaseEntity;
import com.example.task_system.task.Tasks;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Notes extends BaseEntity {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks task;

}
