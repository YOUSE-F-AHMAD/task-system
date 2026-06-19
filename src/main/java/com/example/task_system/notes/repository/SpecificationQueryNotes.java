package com.example.task_system.notes.repository;

import com.example.task_system.notes.Notes;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationQueryNotes {

    public static Specification<Notes> filter(Long taskId){
        return ((root, query, criteriaBuilder) ->
        {

            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("task").get("id"),taskId));

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        }
        );
    }

}
