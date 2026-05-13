package com.example.task_system.user.service;

import com.example.task_system.exception.BusinessException;
import com.example.task_system.exception.ErrorCode;
import com.example.task_system.user.Users;
import com.example.task_system.user.repository.UserRepository;
import com.example.task_system.user.request.ChangePassWordRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(final @NonNull String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userEmail));
    }

    @Override
    public void changePassWord(ChangePassWordRequest request, Long userId) throws Exception {
        final Users saveUser = userRepository.findById(userId)
                .orElseThrow( ()-> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION)
                );

        if (!request.getNewPassword().equals(
                request.getConfirmNewPassword())) throw new BusinessException(ErrorCode.UNCONFIRM_PASSWORD);

        else if (!Objects.equals(this.encoder.encode(request.getNewPassword()),
                saveUser.getPassword())) throw new BusinessException(ErrorCode.ERROR_PASSWORD);

        else{
            saveUser.setPassword(this.encoder.encode(request.getNewPassword()));
            userRepository.save(saveUser);
        }
    }


//    @Override
//    public void registerUser(Users user) {
//        String encodedPassword = encoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userRepository.save(user);
//    }
}