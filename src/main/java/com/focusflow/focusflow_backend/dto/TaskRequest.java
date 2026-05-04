package com.focusflow.focusflow_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title too long")
    public String title;

    @Size(max = 500, message = "Description too long")
    public String description;
}
