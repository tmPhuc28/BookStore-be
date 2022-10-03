package com.bookstore.spring.boot.repositories;

import com.bookstore.spring.boot.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    List<CategoryEntity> findByCategoryName(String categoryName);

    CategoryEntity getById(String id);
}
