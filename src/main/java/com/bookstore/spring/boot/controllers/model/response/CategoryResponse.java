package com.bookstore.spring.boot.controllers.model.response;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.entities.CategoryEntity;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String id;
    private String userId;
    private String categoryName;
    private Status status;

    public CategoryResponse(CategoryEntity category) {
        this.id = category.getId();
        this.userId = category.getUserId();
        this.categoryName = category.getCategoryName();
        this.status = category.getStatus();
    }
}
