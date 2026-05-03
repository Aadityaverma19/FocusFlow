package com.focusflow.focusflow_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.focusflow.focusflow_backend.entity.Task;
import com.focusflow.focusflow_backend.entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByUser(User user);


}
