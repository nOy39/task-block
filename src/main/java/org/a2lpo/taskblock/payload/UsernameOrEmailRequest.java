package org.a2lpo.taskblock.payload;

import lombok.Data;

@Data
public class UsernameOrEmailRequest {
    private String username;
    private String email;
}
