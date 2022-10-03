package com.bookstore.spring.boot.repositories;

import com.bookstore.spring.boot.entities.Authenticated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticatedRepository extends JpaRepository<Authenticated, String> {
    Authenticated getById(String id);
    Authenticated getByUserName(String userName);
}
