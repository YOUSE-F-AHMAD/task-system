package com.example.task_system.user.repository;

import com.example.task_system.user.Users;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<Users> findByEmailIgnoreCase(@NonNull String email);

    Optional<Users> findByIdentifierOrEmail(String identifier, String email);

}
