package com.focusflow.focusflow_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.focusflow.focusflow_backend.dto.TaskRequest;
import com.focusflow.focusflow_backend.dto.TaskResponse;
import com.focusflow.focusflow_backend.entity.Task;
import com.focusflow.focusflow_backend.entity.User;
import com.focusflow.focusflow_backend.repository.TaskRepository;
import com.focusflow.focusflow_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public TaskResponse mapToResponse(Task task){
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }

    public TaskResponse createTask(TaskRequest request){
        Task task = Task.builder()
                    .title(request.title)
                    .description(request.description)
                    .completed(false)
                    .user(getCurrentUser())
                    .build();
        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public List<TaskResponse> getMyTasks(){
        return taskRepository.findByUser(getCurrentUser())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse toggleTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(!task.isCompleted());
        return mapToResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }


}
