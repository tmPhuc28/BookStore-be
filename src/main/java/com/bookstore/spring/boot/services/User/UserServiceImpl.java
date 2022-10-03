package com.bookstore.spring.boot.services.User;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.enums.UserRole;
import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.UserRequest;
import com.bookstore.spring.boot.entities.UserEntity;
import com.bookstore.spring.boot.repositories.CategoryRepository;
import com.bookstore.spring.boot.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    final UserRepository userRepository;
    final CategoryRepository categoryRepository;

    public UserServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public UserEntity createUser(UserRequest userRequest) {
        UserEntity user = new UserEntity();
        Optional<UserEntity> username = userRepository.findByUserName(user.getUserName());

        if(userRequest.getUserName().isEmpty() || userRequest.getUserName() == null || username.isPresent()){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Username was existed or Username is null");
        }

        if(userRequest.getPhoneNumber().isEmpty() || userRequest.getPhoneNumber() == null){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "");
        }

        user.setId(UniqueID.getUUID());
        user.setFistName(userRequest.getFistName());
        user.setLastName(userRequest.getLastName());
        user.setUserName(userRequest.getUserName());
        user.setPasswordHash(userRequest.getPasswordHash());
        user.setDob(userRequest.getDob());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAddress(userRequest.getAddress());
        user.setGender(userRequest.getGender());
        user.setStatus(Status.IN_ACTIVE);
        user.setRole(UserRole.CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public UserEntity update(String userId, UserRequest userRequest) {
        UserEntity user = userRepository.getById(userId);
        user.getId();
        user.setFistName(userRequest.getFistName());
        user.setLastName(userRequest.getLastName());
        user.getUserName();
        user.setPasswordHash(userRequest.getPasswordHash());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setGender(userRequest.getGender());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDob(userRequest.getDob());
        user.setStatus(userRequest.getStatus());
        user.setRole(userRequest.getRole());

        userRepository.save(user);
        return user;
    }

    @Override
    public List<UserEntity> getUsers() {
        List<UserEntity> getUsers = userRepository.findAll();
        return getUsers.isEmpty() ? null : getUsers;
    }

    @Override
    public Page<UserEntity> getUserPages(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return userRepository.findAll(pageRequest);
    }

    @Override
    public UserEntity getUser(String userId) {
        return userRepository.getById(userId);
    }

    @Override
    public UserEntity delete(String id) {
        UserEntity user = userRepository.getById(id);
        user.setStatus(Status.IN_ACTIVE);

        return userRepository.save(user);
    }

    public UserEntity verifyAccount(String id){
        UserEntity user = userRepository.getById(id);
        user.setStatus(Status.ACTIVE);

        return userRepository.save(user);
    }
}

