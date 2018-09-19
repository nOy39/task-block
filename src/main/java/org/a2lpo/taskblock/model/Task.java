package org.a2lpo.taskblock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 30, min = 3)
    private String name;

    @Column(length = 4096)
    private String description;
    private String color;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime expiredDate;
    @NotNull
    private Boolean isDone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_task_id")
    private Task parentId;

    public Task() {
    }

    public Task(@NotBlank @Size(max = 30, min = 3) String name,
                String description,
                String color,
                String imageUrl,
                LocalDateTime created,
                LocalDateTime expiredDate,
                Boolean isDone){
        this.name = name;
        this.description = description;
        this.color = color;
        this.imageUrl = imageUrl;
        this.created = created;
        this.expiredDate = expiredDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", created=" + created +
                ", expiredDate=" + expiredDate +
                ", isDone=" + isDone +
                ", user=" + user +
                ", parentId=" + parentId +
                '}';
    }
}
