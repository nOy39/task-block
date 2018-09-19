package org.a2lpo.taskblock.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class TaskRequest {

    @NotBlank
    private String name;
    private String description;
    private String color;
    private String imageUrl;
    private LocalDateTime expiredDate;
    private Long subTask;
    private boolean isDone;

    public TaskRequest() {
    }

    @Override
    public String toString() {
        return "TaskRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", expiredDate=" + expiredDate +
                ", subTask=" + subTask +
                ", isDone=" + isDone +
                '}';
    }
}
