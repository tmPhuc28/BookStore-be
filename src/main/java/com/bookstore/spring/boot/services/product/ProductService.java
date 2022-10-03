package com.bookstore.spring.boot.services.product;

import com.bookstore.spring.boot.controllers.model.request.ProductRequest;
import com.bookstore.spring.boot.entities.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductEntity createProduct(ProductRequest product);

    ProductEntity updateProduct(String productId, ProductRequest productRequest);

    List<ProductEntity> getProducts();

    Page<ProductEntity> getPageProduct(int pageNumber, int pageSize);

    ProductEntity getProduct(String productId);

    Boolean delProduct(String productId);
}
