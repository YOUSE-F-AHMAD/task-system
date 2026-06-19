package com.example.task_system.noteBook.repository;

import com.example.task_system.userNoteBook.UserNoteBook;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationQueryNoteBook {



    public static Specification<UserNoteBook> userNoteBookSpecByUserId(Long id){
        return ((
                root,
                 query,
                 criteriaBuilder
        ) ->
            {
              List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
              predicates.add(criteriaBuilder.equal(root.get("user").get("id"),id));
              return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
            }
        );
    }

}
