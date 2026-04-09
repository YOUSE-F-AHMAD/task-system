package com.example.task_system.auth.service;

import com.example.task_system.auth.request.AuthLoginRequest;
import com.example.task_system.auth.request.AuthRegisterRequest;
import com.example.task_system.auth.response.AuthLoginResponse;

public interface AuthService {

    AuthLoginResponse login(AuthLoginRequest request);

    void register(AuthRegisterRequest request);

}
