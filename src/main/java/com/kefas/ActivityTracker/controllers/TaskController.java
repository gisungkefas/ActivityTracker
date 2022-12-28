package com.kefas.ActivityTracker.controllers;

import com.kefas.Weekeighttask.dto.TaskDto;
import com.kefas.Weekeighttask.exception.TaskAlreadyExistException;
import com.kefas.Weekeighttask.exception.TaskNotFoundException;
import com.kefas.Weekeighttask.exception.UsersNotFoundException;
import com.kefas.Weekeighttask.service.serviceImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/task")
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping("/{usersId}/createTask")
    public ResponseEntity<String> createTask(@Valid @PathVariable Long usersId, @RequestBody TaskDto taskDto) throws TaskAlreadyExistException, UsersNotFoundException {
        taskService.createTask(usersId, taskDto);
        return new ResponseEntity<>("New Task Created Successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("/{taskId}/updateTask")
    public ResponseEntity<String> updateTask(@RequestBody TaskDto taskDto,
                                             @PathVariable Long taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.updateTask(taskDto, taskId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long taskId,
                                                    @RequestParam("status") String status) throws TaskNotFoundException {
        TaskDto task = taskService.updateTaskStatus(taskId, status);
        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{taskId}/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestBody TaskDto taskDto,@PathVariable Long taskId) throws TaskNotFoundException {
        return new ResponseEntity<>(taskService.deleteTask(taskDto, taskId), HttpStatus.ACCEPTED);
    }
}
