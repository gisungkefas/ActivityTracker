package com.kefas.ActivityTracker.repository;

import com.kefas.ActivityTracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTaskByTitle(String title);
    Optional<Task> findTaskByStatus(String status);
}
