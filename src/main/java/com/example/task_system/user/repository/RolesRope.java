package com.example.task_system.user.repository;

import com.example.task_system.user.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRope extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByName(String userRole);

}
