package com.bookstore.spring.boot.services.User;

import com.bookstore.spring.boot.controllers.model.request.UserRequest;
import com.bookstore.spring.boot.entities.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserRequest userRequest);

    UserEntity update(String userId, UserRequest user);

    List<UserEntity> getUsers();

    Page<UserEntity> getUserPages(int pageNumber, int pageSize);

    UserEntity getUser(String userId);

    UserEntity delete(String id);

    UserEntity verifyAccount(String id);
}
