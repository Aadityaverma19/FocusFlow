package com.focusflow.focusflow_backend.dto;

public class RegisterRequest {
    public String name;
    public String email;
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

}
