package org.a2lpo.taskblock.controllers;

import org.a2lpo.taskblock.model.User;
import org.a2lpo.taskblock.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String testString() {
        return "Server connection good.";
    }

    @GetMapping("{id}")
    public User user(@PathVariable("id") String id) {
        return userRepo.findById(Long.valueOf(id)).get();
    }
}
