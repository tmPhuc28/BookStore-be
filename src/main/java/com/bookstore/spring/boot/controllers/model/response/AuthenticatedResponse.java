package com.bookstore.spring.boot.controllers.model.response;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.enums.UserRole;
import com.bookstore.spring.boot.entities.Authenticated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedResponse {
    private String id;
    private String userId;
    private String userName;
    private Status status;
    private UserRole role;

    public AuthenticatedResponse(Authenticated auth){
        this.id = auth.getId();
        this.userId = auth.getUserId();
        this.userName = auth.getUserName();
        this.status = auth.getStatus();
        this.role = auth.getRole();
    }
}
