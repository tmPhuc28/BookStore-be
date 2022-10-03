package com.bookstore.spring.boot.controllers.model.response;

import com.bookstore.spring.boot.common.enums.Gender;
import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.enums.UserRole;
import com.bookstore.spring.boot.entities.UserEntity;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String fistName;
    private String lastName;
    private String userName;
    private String passwordHash;
    private String email;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private Status status;
    private String dob;
    private UserRole role;

    public UserResponse(UserEntity user){
        this.id = user.getId();
        this.fistName = user.getFistName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.passwordHash = user.getPasswordHash();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.dob = user.getDob().toString();
        this.status = user.getStatus();
        this.role = user.getRole();
    }
}
