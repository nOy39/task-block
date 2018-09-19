package org.a2lpo.taskblock.controllers;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.model.User;
import org.a2lpo.taskblock.payload.TaskPeriodRequest;
import org.a2lpo.taskblock.payload.TaskRequest;
import org.a2lpo.taskblock.payload.TaskResponse;
import org.a2lpo.taskblock.repository.TaskRepo;
import org.a2lpo.taskblock.repository.UserRepo;
import org.a2lpo.taskblock.security.CurrentUser;
import org.a2lpo.taskblock.security.UserPrincipal;
import org.a2lpo.taskblock.utils.TaskUtils;
import org.a2lpo.taskblock.utils.ToolsUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final TaskUtils taskUtils;
    private final ToolsUtils toolsUtils;

    public TaskController(TaskRepo taskRepo,
                          UserRepo userRepo,
                          TaskUtils taskUtils,
                          ToolsUtils toolsUtils) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.taskUtils = taskUtils;
        this.toolsUtils = toolsUtils;
    }

    /**
     * Возвращает payload оглавление родительских задач пользователя
     *
     * @param userPrincipal принимает пользователя по которому делается поиск
     * @return
     */
    @GetMapping
    public List<TaskResponse> userTask(@CurrentUser UserPrincipal userPrincipal) {

        return taskUtils.createResponseList(taskRepo
                        .findAll(userPrincipal.extractUser(userPrincipal)),
                new ArrayList<>());
    }

    //TODO Переделать чтобы выгружал TaskResponse
    /**
     * @param taskRequest
     * @param currentUser
     * @return
     */
    @PostMapping
    public Task createTask(@RequestBody TaskRequest taskRequest,
                           @CurrentUser UserPrincipal currentUser) {
        Task newTask = new Task(taskRequest.getName(),
                taskRequest.getDescription(),
                taskRequest.getColor(),
                taskRequest.getImageUrl(),
                LocalDateTime.now(),
                taskRequest.getExpiredDate(),
                false);
        newTask.setUser(userRepo.findById(currentUser.getId()).get());
        if (taskRequest.getSubTask() != null) {
            newTask.setParentId(taskRepo.findById(taskRequest.getSubTask()).get());
        }
        taskRepo.save(newTask);
        return newTask;
    }

    /**
     * Выгружает все задачи по полям id и parent_id
     * @param id переменная поиска, берется из Pathvariable
     * @param userPrincipal авторизованный пользователь
     * @return возвращает JSON TaskResponse из Payload
     */
    @GetMapping(value = "{id}")
    public List<TaskResponse> testGetById(@PathVariable("id") String id,
                                          @CurrentUser UserPrincipal userPrincipal) {
        return taskUtils.createResponseList(taskRepo
                .findAllById(Long.valueOf(id), userPrincipal.extractUser(userPrincipal)),
                new ArrayList<>());
    }

    /**
     * Выгрузка горящих задач
     * @param userPrincipal авторизованный пользователь
     * @return возвращает JSON TaskResponse из Payload
     */
    @GetMapping("hot")
    public List<TaskResponse> hotTask(@CurrentUser UserPrincipal userPrincipal) {
        LocalDateTime tomorrow = LocalDateTime.now().with(LocalTime.MAX);

        return taskUtils.createResponseList(taskRepo
                        .findAllCurrentDayTask(tomorrow,
                                userPrincipal.extractUser(userPrincipal)),
                new ArrayList<>());
    }

    /**
     * Выгрузка не решённых задач за указаный период,
     * @param periodRequest JSON запрос от клиента с началом и концом периода запроса
     * @param userPrincipal авторизованный пользователь
     * @return возвращает JSON TaskResponse из Payload
     */
    @PostMapping(value = "period")
    public List<TaskResponse> periodTask(@RequestBody TaskPeriodRequest periodRequest,
                                         @CurrentUser UserPrincipal userPrincipal) {
        return taskUtils.createResponseList(
                taskRepo.findAllTaskFromStartToEnd(periodRequest.getStartPeriod(),
                        periodRequest.getEndPeriod(),
                        userPrincipal.extractUser(userPrincipal)),
                new ArrayList<>());
    }

    //TODO Переделать PUT
    @PutMapping("{id}")
    public Task editTask(@RequestBody TaskRequest taskRequest,
                         @CurrentUser UserPrincipal userPrincipal,
                         @PathVariable("id") String id) {
        Task editingTask = taskRepo.findById(Long.valueOf(id)).get();
        if (taskRequest.getName() != null)
            editingTask.setName(taskRequest.getName());
        if (taskRequest.getDescription() != null)
            editingTask.setDescription(taskRequest.getDescription());
        if (taskRequest.getExpiredDate() != null)
            editingTask.setExpiredDate(taskRequest.getExpiredDate());
        if (taskRequest.getColor() != null)
            editingTask.setColor(taskRequest.getColor());
        if (taskRequest.isDone() != false)
            editingTask.setIsDone(taskRequest.isDone());
        if (taskRequest.getSubTask() != null) {
            editingTask.setParentId(taskRepo.findById(taskRequest.getSubTask()).get());
        }
        taskRepo.save(editingTask);
        return editingTask;
    }

    //TODO Разобраться с удалением
    @DeleteMapping("{id}")
    public Boolean deleteTask(@PathVariable("id") String id) {
        try {
            taskRepo.delete(taskRepo.findById(Long.valueOf(id)).get());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
