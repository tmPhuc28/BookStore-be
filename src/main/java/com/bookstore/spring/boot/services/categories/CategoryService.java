package com.bookstore.spring.boot.services.categories;

import com.bookstore.spring.boot.controllers.model.request.CategoryRequest;
import com.bookstore.spring.boot.entities.CategoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    CategoryEntity create(CategoryRequest categoryRequest);

    List<CategoryEntity> getCategories();

    Page<CategoryEntity> getCategoryPages(int pageNumber, int pageSize);

    CategoryEntity update(String categoryId, CategoryRequest categoryRequest);

    CategoryEntity getCategory(String categoryId);

    CategoryEntity delCategory(String categoryId);
}
