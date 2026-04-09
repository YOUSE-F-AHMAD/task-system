package com.example.task_system.user.service;

import com.example.task_system.user.Users;
import com.example.task_system.user.request.ChangePassWordRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void changePassWord(ChangePassWordRequest request, Integer userId) throws Exception;

    void registerUser(Users users);

}
