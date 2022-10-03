package com.bookstore.spring.boot.controllers.model.request;

import com.bookstore.spring.boot.common.enums.Gender;
import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private String fistName;
    private String lastName;
    private String userName;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Date dob;
    private Status status;
    private UserRole role;
}
