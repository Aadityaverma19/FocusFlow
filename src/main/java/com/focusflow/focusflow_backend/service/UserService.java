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
    private final JwtService jwtService;

    public UserService(UserRepository userRepository , BCryptPasswordEncoder passwordEncoder , JwtService jwtService ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    public String login(LoginRequest request){
        User user = userRepository.findByEmail(request.email).orElseThrow(() -> new RuntimeException("User not found")) ; 

        boolean success = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if(success){
            return jwtService.generateToken(user.getEmail());
        }
        throw new RuntimeException("Invalid password");
    }

}
