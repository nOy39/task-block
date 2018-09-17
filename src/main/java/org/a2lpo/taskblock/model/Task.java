package org.a2lpo.taskblock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    String name;

    @Column(length = 4096)
    String description;
    String color;
    String imageUrl;
    LocalDateTime created;
    LocalDateTime expiredDate;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task subTask;

    public Task(@NotBlank @Size(max = 30, min = 3) String name,
                String description,
                String color,
                String imageUrl,
                LocalDateTime created,
                LocalDateTime expiredDate) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.imageUrl = imageUrl;
        this.created = created;
        this.expiredDate = expiredDate;
    }
}
