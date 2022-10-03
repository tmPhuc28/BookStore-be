package com.bookstore.spring.boot.repositories;

import com.bookstore.spring.boot.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem, String> {
    Boolean existsByProductId(String productId);

    CartItem getByProductId(String productId);

    CartItem getById(String id);

    List<CartItem> findAllById(String strings);

    List<CartItem> findAllByUserId(String id);
}
