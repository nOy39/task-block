package org.a2lpo.taskblock.controllers;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.payload.TaskRequest;
import org.a2lpo.taskblock.repository.TaskRepo;
import org.a2lpo.taskblock.repository.UserRepo;
import org.a2lpo.taskblock.security.CurrentUser;
import org.a2lpo.taskblock.security.UserPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskController(TaskRepo taskRepo,
                          UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("test/{id}")
    public Long testTask(@PathVariable("id") String id) {
        return taskRepo.countDistinctBySubTask(taskRepo.findById(Long.valueOf(id)).get());
    }

    @GetMapping
    public List<Task> getTitleTask(@CurrentUser UserPrincipal currentUser) {
        List<Task> allByUserAndSubTaskNull = taskRepo.findAllByUserAndSubTaskNull(
                userRepo.findById(currentUser.getId()).get());
        return allByUserAndSubTaskNull;
    }

    @GetMapping("{id}")
    public List<Task> getChildTask(@PathVariable("id") String id) {
        return taskRepo.findBySubTask(taskRepo.findById(Long.valueOf(id)).get());
    }

    @PostMapping
    public Boolean createTask(@RequestBody TaskRequest taskRequest,
                              @CurrentUser UserPrincipal currentUser) {
        Task newTask = new Task(taskRequest.getName(),
                taskRequest.getDescription(),
                taskRequest.getColor(),
                taskRequest.getImageUrl(),
                LocalDateTime.now(),
                taskRequest.getExpiredDate());
        newTask.setUser(userRepo.findById(currentUser.getId()).get());
        newTask.setSubTask(taskRepo.findById(taskRequest.getSubTask()).get());
        taskRepo.save(newTask);
        return false;
    }
}
