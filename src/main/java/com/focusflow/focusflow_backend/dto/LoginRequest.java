package com.focusflow.focusflow_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Email required")
    public String email;

    @NotBlank(message = "Password required")
    private String password;

    public String getPassword(){
        return this.password;
    }
    
}
