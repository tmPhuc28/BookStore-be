package com.bookstore.spring.boot.repositories;
import com.bookstore.spring.boot.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String userName);

    UserEntity getById(String userId);

    UserEntity getByEmail(String email);

    UserEntity getByPhoneNumber(String phone);
}
