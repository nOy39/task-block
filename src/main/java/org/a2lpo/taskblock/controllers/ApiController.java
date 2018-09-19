package org.a2lpo.taskblock.controllers;

import org.a2lpo.taskblock.model.Task;
import org.a2lpo.taskblock.payload.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping("info")
    public HashMap<String, ApiDescriptionResponse> info() {
        HashMap<String, ApiDescriptionResponse> apiInfo = new HashMap<>();
        apiInfo.put("{POST}{/api/auth/signup}", new ApiDescriptionResponse(
                "/api/auth/signup",
                "POST",
                "Регистрация нового пользователя",
                new SignUpRequest().toString(),
                new ApiResponse().toString(),
                false
        ));
        apiInfo.put("{POST}{/api/auth/signin}", new ApiDescriptionResponse(
                "/api/auth/signin",
                "POST",
                "Авторизация пользователя",
                new LoginRequest().toString(),
                "SignInResponse{accessToken= '<<TOKEN>>', tokenType='TOKEN_TYPE'",
                false)
        );
        apiInfo.put("{GET}{/api/auth/me}", new ApiDescriptionResponse(
                "/api/auth/me",
                "GET",
                "Информация об авторизированном пользователе",
                null,
                new UserResponse().toString(),
                true
        ));
        apiInfo.put("{POST}{/api/tasks}", new ApiDescriptionResponse(
                "/api/tasks",
                "POST",
                "Создание новой задачи",
                new TaskRequest().toString(),
                new Task().toString(),
                true
        ));
        apiInfo.put("{GET}{/api/tasks}", new ApiDescriptionResponse(
                "/api/tasks",
                "GET",
                "Отображает список родительских задач пользователя",
                null,
                "List<"+ new TaskRequest().toString() +">",
                true
        ));
        apiInfo.put("{GET}{/api/tasks/hot}", new ApiDescriptionResponse(
                "/api/tasks/hot",
                "GET",
                "Горящие невыполненные задачи.",
                null,
                new TaskResponse().toString(),
                true
        ));
        return apiInfo;
    }
}
