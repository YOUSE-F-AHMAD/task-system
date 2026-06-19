package com.example.task_system.config;

import com.example.task_system.user.Users;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long>  getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                !(authentication.getPrincipal() instanceof Users) ) return Optional.of(0L);

        final Users user = (Users) authentication.getPrincipal();
            if (user != null) {
                return Optional.ofNullable(user.getId());
            }

         return Optional.empty();
    }
}
