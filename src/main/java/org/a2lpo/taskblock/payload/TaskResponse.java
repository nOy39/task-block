package org.a2lpo.taskblock.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String name;
    private String color;
    private String imageUrl;
    private String description;
    private LocalDateTime created;
    private LocalDateTime expiredDate;
    private Boolean isDone;
    private Long subTaskCount;
    private Long parentId;
    private String parentName;

    public TaskResponse() {
    }

    public TaskResponse(Long parentId, String parentName) {
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public TaskResponse(Long id,
                        String name,
                        String color,
                        String imageUrl,
                        LocalDateTime created,
                        LocalDateTime expiredDate,
                        Boolean isDone,
                        Long subTaskCount) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.imageUrl = imageUrl;
        this.created = created;
        this.expiredDate = expiredDate;
        this.isDone = isDone;
        this.subTaskCount = subTaskCount;
    }

    @Override
    public String toString() {
        return "TaskResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", expiredDate=" + expiredDate +
                ", isDone=" + isDone +
                ", subTaskCount=" + subTaskCount +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
