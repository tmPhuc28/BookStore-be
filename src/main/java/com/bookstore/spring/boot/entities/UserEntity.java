package com.bookstore.spring.boot.entities;

import com.bookstore.spring.boot.common.enums.Gender;
import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity{

    @Id
    @Column(name = "id", length = 32, nullable = false, updatable = false)
    private String id;
    private UserRole role;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password_hash", length = 32)
    private String passwordHash;

    @Column(name = "fist_name", length = 255)
    private String fistName;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
