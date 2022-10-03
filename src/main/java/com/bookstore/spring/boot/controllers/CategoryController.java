package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.controllers.model.request.CategoryRequest;
import com.bookstore.spring.boot.controllers.model.response.CategoryResponse;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.entities.CategoryEntity;
import com.bookstore.spring.boot.services.categories.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.CATEGORY_API)
@Slf4j
public class CategoryController extends AbstractBaseController {
    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create new category
    @PostMapping
    public ResponseEntity<RestAPIResponse> createCategory(
            @RequestBody CategoryRequest categoryRequest) {
        CategoryEntity category = categoryService.create(categoryRequest);
        return responseUtil.successResponse(new CategoryResponse(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> getCategory( @PathVariable  String id) {
        return responseUtil.successResponse(new CategoryResponse(categoryService.getCategory(id)), "");
    }

    @GetMapping()
    public ResponseEntity<RestAPIResponse> getCategories(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return responseUtil.successResponse(
                new PagingResponse(categoryService.getCategories(),
                        categoryService.getCategoryPages(pageNumber, pageSize)
                ), "");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestAPIResponse> updateCategory(
            @PathVariable String id,
            @RequestBody CategoryRequest categoryRequest
    ) {
        CategoryEntity category = categoryService.update(id, categoryRequest);
        return responseUtil.successResponse(new CategoryResponse(category), "");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> deleteCategory( @PathVariable  String id) {
        return responseUtil.successResponse(categoryService.delCategory(id));
    }
}
