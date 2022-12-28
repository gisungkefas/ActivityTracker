package com.kefas.ActivityTracker.service;

import com.kefas.ActivityTracker.dto.TaskDto;
import com.kefas.ActivityTracker.exception.TaskAlreadyExistException;
import com.kefas.ActivityTracker.exception.TaskNotFoundException;
import com.kefas.ActivityTracker.exception.UsersNotFoundException;

public interface TaskService {

    void createTask(Long usersId, TaskDto taskDto) throws TaskAlreadyExistException, UsersNotFoundException;
    String updateTask(TaskDto taskDto,Long id) throws TaskNotFoundException;
    String deleteTask(TaskDto taskDto,Long id) throws TaskNotFoundException;
    TaskDto updateTaskStatus(Long taskId, String status) throws UsersNotFoundException, TaskNotFoundException;
}
