package com.example.task_system.task.repository;

import com.example.task_system.task.Tasks;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationQuery {


    public static Specification<Tasks> filter(Long noteBookId){
        return ((root, query, criteriaBuilder) ->
            {

              List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();
              predicates.add(criteriaBuilder.equal(root.get("noteBook").get("id"),noteBookId));

              return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
            }
        );
    }

}
