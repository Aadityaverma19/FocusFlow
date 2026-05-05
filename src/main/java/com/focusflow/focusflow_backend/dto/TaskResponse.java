package com.focusflow.focusflow_backend.dto;

public class TaskResponse {
    public Long id;
    public String title;
    public String description;
    public Boolean completed;
    public TaskResponse(Long id, String title, String description, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }    
}   
