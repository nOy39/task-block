package org.a2lpo.taskblock.payload;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
public class UserResponse {

    private String name;
    private String username;
    private String email;
    List roles;

    public UserResponse(String name, String username, String email, List roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public UserResponse(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }


}
