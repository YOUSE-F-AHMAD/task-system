package com.example.task_system.user.service;

import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepo;
import com.example.task_system.user.request.ChangePassWordRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder encoder;
    private final UserRepo userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(final @NonNull String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(userEmail)
                .orElse(new Users());
    }

    @Override
    public void changePassWord(ChangePassWordRequest request, Integer userId) throws Exception {
        final Users saveUser = userRepository.findById(userId)
                .orElse(new Users());

        if (!request.getNewPassword().equals(
                request.getConfirmNewPassword())) throw new Exception("confirm your password");

        else if (!Objects.equals(this.encoder.encode(request.getNewPassword()),
                saveUser.getPassword())) throw new Exception("your password is not right");

        else{
            saveUser.setPassword(this.encoder.encode(request.getNewPassword()));
            userRepository.save(saveUser);
        }
    }


    @Override
    public void registerUser(Users user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}