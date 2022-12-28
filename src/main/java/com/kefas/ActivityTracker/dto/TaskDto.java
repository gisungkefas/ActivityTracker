package com.kefas.ActivityTracker.dto;

import com.kefas.ActivityTracker.emun.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TaskDto{

    @NotBlank(message = "Title should not be blank")
    private String title;

    @NotBlank(message = "Description should not be blank")
    private String description;

    @NotBlank(message = "Status should not be blank" )
    private Status status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime completedDate;
}
