package com.focusflow.focusflow_backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.focusflow.focusflow_backend.dto.LoginRequest;
import com.focusflow.focusflow_backend.dto.RegisterRequest;
import com.focusflow.focusflow_backend.dto.UserResponse;
import com.focusflow.focusflow_backend.entity.User;
import com.focusflow.focusflow_backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository , BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest request){
        User user = new User(
            request.name,
            request.email,
            passwordEncoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail()
        );
        return response;
    }

    public boolean login(LoginRequest request){
        User user = userRepository.findByEmail(request.email).orElse(null);

        if (user == null){
            return false;
        }

        return passwordEncoder.matches(request.getPassword(),user.getPassword());
    }

}
