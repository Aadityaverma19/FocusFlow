package com.focusflow.focusflow_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.focusflow.focusflow_backend.entity.Task;
import com.focusflow.focusflow_backend.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getMyTasks(){
        return taskService.getMyTasks();
    }

    @PutMapping("/{id}")
    public Task toggleTask(@PathVariable Long id){
        return taskService.toggleTask(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}
