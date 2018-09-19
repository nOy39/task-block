package org.a2lpo.taskblock.controllers;

import org.a2lpo.taskblock.payload.UsernameOrEmailRequest;
import org.a2lpo.taskblock.repository.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("checkUsernameAvailability")
    public Boolean checkUsernameAvailability(@RequestBody UsernameOrEmailRequest usernameRequest) {
        return userRepo.existsByUsername(usernameRequest.getUsername());
    }

    @PostMapping("checkEmailAvailability")
    public Boolean checkEmailAvailability(@RequestBody UsernameOrEmailRequest emailRequest) {
        return userRepo.existsByEmail(emailRequest.getEmail());
    }
}
