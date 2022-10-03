package com.bookstore.spring.boot.services.categories;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.CategoryRequest;
import com.bookstore.spring.boot.entities.Authenticated;
import com.bookstore.spring.boot.entities.CategoryEntity;
import com.bookstore.spring.boot.repositories.AuthenticatedRepository;
import com.bookstore.spring.boot.repositories.CategoryRepository;
import com.bookstore.spring.boot.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;
    final AuthenticatedRepository authenticatedRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, AuthenticatedRepository auth) {
        this.categoryRepository = categoryRepository;
        this.authenticatedRepository = auth;
    }

    @Override
    public CategoryEntity create(CategoryRequest categoryRequest) {
        if (!authenticatedRepository.existsById(categoryRequest.getAuthToken()) ||
                categoryRequest.getAuthToken().isEmpty() || categoryRequest.getAuthToken() == null) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "User id not null");
        }

        Authenticated auth = authenticatedRepository.getById(categoryRequest.getAuthToken());
        CategoryEntity category = new CategoryEntity();

        if (categoryRequest.getCategoryName().isEmpty() || categoryRequest.getCategoryName() == null) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Category is null");
        }

        category.setId(UniqueID.getUUID());
        category.setUserId(auth.getUserId());
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setStatus(categoryRequest.getStatus());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public List<CategoryEntity> getCategories() {
        List<CategoryEntity> getCategories = categoryRepository.findAll();
        if (!getCategories.isEmpty()) {
            return getCategories;
        }
        return null;
    }

    @Override
    public Page<CategoryEntity> getCategoryPages(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public CategoryEntity update(String categoryId, CategoryRequest categoryRequest) {
        if (!authenticatedRepository.existsById(categoryRequest.getAuthToken()) ||
                categoryRequest.getAuthToken().isEmpty() || categoryRequest.getAuthToken() == null) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "User id not null");
        }

        Authenticated auth = authenticatedRepository.getById(categoryRequest.getAuthToken());
        CategoryEntity category = categoryRepository.getById(categoryId);

        if (categoryRequest.getCategoryName().isEmpty() || categoryRequest.getCategoryName() == null) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Category is null");
        }

        category.getId();
        category.setUserId(auth.getUserId());
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setStatus(categoryRequest.getStatus());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public CategoryEntity getCategory(String categoryId) {
        return categoryRepository.getById(categoryId);
    }

    @Override
    public CategoryEntity delCategory(String categoryId) {
        CategoryEntity category = categoryRepository.getById(categoryId);
        category.setStatus(Status.IN_ACTIVE);
        return categoryRepository.save(category);
    }
}
