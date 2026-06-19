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
    public String changePassWord(Long id ,ChangePassWordRequest request){
        final Users saveUser = userRepository.findById(id)
                .orElseThrow( ()-> new BusinessException(ErrorCode.USER_NOT_FOUND_EXCEPTION)
                );

        if (!request.getNewPassword().equals(request.getConfirmNewPassword()))
            throw new BusinessException(ErrorCode.UNCONFIRM_PASSWORD);

        else if (!encoder.matches(request.getNewPassword(), saveUser.getPassword()))
            throw new BusinessException(ErrorCode.ERROR_PASSWORD);

        else
        {
            saveUser.setPassword(this.encoder.encode(request.getNewPassword()));
            userRepository.save(saveUser);
            return "Password changed successfully";
        }
    }

}