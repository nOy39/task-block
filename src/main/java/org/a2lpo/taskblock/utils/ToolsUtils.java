package org.a2lpo.taskblock.utils;

import org.a2lpo.taskblock.model.User;
import org.a2lpo.taskblock.security.UserPrincipal;
import org.springframework.stereotype.Component;

@Component
public class ToolsUtils {

    public User extractUser(UserPrincipal userPrincipal) {
        return new User(
                userPrincipal.getId(),
                userPrincipal.getName(),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getPassword()
        );
    }
}
