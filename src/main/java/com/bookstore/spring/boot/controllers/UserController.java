package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.controllers.model.request.UserRequest;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.controllers.model.response.UserResponse;
import com.bookstore.spring.boot.entities.UserEntity;
import com.bookstore.spring.boot.services.User.UserService;
import lombok.CustomLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.USER_API)
@Slf4j
public class UserController extends AbstractBaseController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<RestAPIResponse> signUp(@RequestBody UserRequest userRequest) {
        return responseUtil.successResponse(new UserResponse(userService.createUser(userRequest)), "");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> getUser(@PathVariable String id) {
        return responseUtil.successResponse(new UserResponse(userService.getUser(id)));
    }

    @GetMapping
    public ResponseEntity<RestAPIResponse> getUsers(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return responseUtil.successResponse(
                new PagingResponse(userService.getUsers(),
                        userService.getUserPages(pageNumber, pageSize)), "");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RestAPIResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
        return responseUtil.successResponse(new UserResponse(userService.update(id, userRequest)));
    }

    @PutMapping(path = "/verify-account/{id}")
    public ResponseEntity<RestAPIResponse> verifyAccount(
            @PathVariable String id) {
        UserEntity user = userService.verifyAccount(id);
        return responseUtil.successResponse(new UserResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> deleteUser(@PathVariable String id) {
        return responseUtil.successResponse(new UserResponse(userService.delete(id)), "");
    }
}
