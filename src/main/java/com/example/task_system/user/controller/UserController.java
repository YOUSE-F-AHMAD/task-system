package com.example.task_system.user.controller;

import com.example.task_system.user.request.ChangePassWordRequest;
import com.example.task_system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/api/v1/users/{userID}/change-password")
    public ResponseEntity<String> changePassWord(
            @PathVariable("userID") Long userID,
            @RequestBody ChangePassWordRequest request
    ) {
        return ResponseEntity.ok(userService.changePassWord(userID,request));
    }
}
