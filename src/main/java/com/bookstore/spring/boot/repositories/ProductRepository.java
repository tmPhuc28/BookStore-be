package com.bookstore.spring.boot.repositories;

import com.bookstore.spring.boot.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByProductName(String productName);

    ProductEntity getById(String productId);
}
