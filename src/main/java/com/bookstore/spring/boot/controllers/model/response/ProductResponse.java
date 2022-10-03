package com.bookstore.spring.boot.controllers.model.response;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.entities.ProductEntity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Status status;
    private String userId;
    private String id;
    private String productName;
    private String productImage;
    private double price;
    private int quantity;
    private String description;
    private String manufacture;
    private String author;
    private String categoryId;
    public ProductResponse(ProductEntity product){
        this.id = product.getId();
        this.userId = product.getUserId();
        this.productName = product.getProductName();
        this.productImage = product.getProductImage();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.manufacture = product.getManufacture();
        this.author = product.getAuthor();
        this.categoryId = product.getCategoryId();
        this.status = product.getStatus();
    }
}
