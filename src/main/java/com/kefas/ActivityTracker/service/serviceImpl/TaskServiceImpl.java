package com.kefas.ActivityTracker.service.serviceImpl;

import com.kefas.ActivityTracker.dto.TaskDto;
import com.kefas.ActivityTracker.emun.Status;
import com.kefas.ActivityTracker.entity.Task;
import com.kefas.ActivityTracker.entity.Users;
import com.kefas.ActivityTracker.exception.TaskAlreadyExistException;
import com.kefas.ActivityTracker.exception.TaskNotFoundException;
import com.kefas.ActivityTracker.exception.UsersNotFoundException;
import com.kefas.ActivityTracker.repository.TaskRepository;
import com.kefas.ActivityTracker.repository.UsersRepository;
import com.kefas.ActivityTracker.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UsersRepository usersRepository;

    @Override
    public void createTask(Long usersId, TaskDto taskDto) throws TaskAlreadyExistException, UsersNotFoundException {
        Users users = usersRepository.findById(usersId)
        .orElseThrow(() -> new UsersNotFoundException("User not fount"));

        Task task = new Task();
        boolean existTaskByTitle = taskRepository.findTaskByTitle(taskDto.getTitle()).isPresent();
        if(existTaskByTitle){
            throw new TaskAlreadyExistException("This task Already Exist create new one");
        }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setUser(users);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public String updateTask(TaskDto taskDto, Long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).
                orElseThrow(()-> new TaskNotFoundException("Task Not Found"));

        if(taskDto.getTitle() != null && !taskDto.getTitle().equals(task.getTitle())){
            task.setTitle(taskDto.getTitle());
        }

        if(taskDto.getDescription() != null && !taskDto.getDescription().equals(task.getDescription())){
            task.setDescription(taskDto.getDescription());
        }
        task.setStatus(taskDto.getStatus());
        taskRepository.save(task);
        return "Task Updated Successfully";
    }

    @Override
    public String deleteTask(TaskDto taskDto, Long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId).
                orElseThrow(()-> new TaskNotFoundException("Task Not Found"));
        task.setStatus(taskDto.getStatus());
        taskRepository.delete(task);
        return "Task Deleted Successfully";
    }

    @Override
    public TaskDto updateTaskStatus(Long taskId, String status) throws TaskNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new TaskNotFoundException("Task not found"));

        Status userStatus = viewStatus(status);

        if(!task.getStatus().equals(userStatus)){
            task.setStatus(userStatus);
            if(userStatus.equals(Status.DONE)){
                task.setCompletedDate(LocalDateTime.now());
            }else {
                task.setCompletedDate(null);
            }
        }
        task = taskRepository.save(task);

        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto);

        return taskDto;
    }

    private Status viewStatus(String status) throws TaskNotFoundException {
        Status statusCheck;

        if(status.equalsIgnoreCase("pending")){
            statusCheck = Status.PENDING;
        } else if (status.equalsIgnoreCase("in_progress")) {
            statusCheck = Status.IN_PROGRESS;
        } else if (status.equalsIgnoreCase("done")){
            statusCheck = Status.DONE;
        } else {
            throw new TaskNotFoundException("Invalid Status");
        }

        return statusCheck;
    }

}
