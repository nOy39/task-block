package org.a2lpo.taskblock.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private String name;
    private String username;
    private String email;
    List roles;

    public UserResponse() {
    }

    public UserResponse(String name, String username, String email, List roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
