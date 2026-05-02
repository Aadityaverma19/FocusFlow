package com.focusflow.focusflow_backend.dto;

public class LoginRequest {
    public String email;
    private String password;

    public String getPassword(){
        return this.password;
    }
    
}
