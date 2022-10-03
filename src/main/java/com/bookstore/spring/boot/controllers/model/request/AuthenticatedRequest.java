package com.bookstore.spring.boot.controllers.model.request;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticatedRequest {
    private String userName;
    private String password;
    private UserRole role;
}