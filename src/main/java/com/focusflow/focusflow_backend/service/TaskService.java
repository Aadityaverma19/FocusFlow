package com.focusflow.focusflow_backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.focusflow.focusflow_backend.entity.Task;
import com.focusflow.focusflow_backend.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private String getCurrentUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Task createTask(Task task){
        task.setUserEmail(getCurrentUserEmail());
        return taskRepository.save(task);
    }

    public List<Task> getMyTasks(){
        return taskRepository.findByUserEmail(getCurrentUserEmail());
    }

    public Task toggleTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }


}
