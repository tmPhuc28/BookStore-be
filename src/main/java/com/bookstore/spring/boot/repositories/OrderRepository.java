package com.bookstore.spring.boot.repositories;

import com.bookstore.spring.boot.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(String userId);

    OrderEntity getById(String id);
}
