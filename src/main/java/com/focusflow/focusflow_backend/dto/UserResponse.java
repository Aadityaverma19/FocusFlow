package com.focusflow.focusflow_backend.dto;

public class UserResponse {
    public Long id;
    public String name;
    public String email;

    public UserResponse(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    

    
}
