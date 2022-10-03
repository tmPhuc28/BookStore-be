package com.bookstore.spring.boot.controllers;

import com.bookstore.spring.boot.common.utils.RestAPIResponse;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.controllers.model.request.ProductRequest;
import com.bookstore.spring.boot.controllers.model.response.PagingResponse;
import com.bookstore.spring.boot.controllers.model.response.ProductResponse;
import com.bookstore.spring.boot.entities.ProductEntity;
import com.bookstore.spring.boot.repositories.ProductRepository;
import com.bookstore.spring.boot.services.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiPath.PRODUCT_API)
@Slf4j
public class ProductController extends AbstractBaseController {
    final ProductService productService;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<RestAPIResponse> createProduct(
            @RequestBody ProductRequest product) {
        ProductEntity productEntity = productService.createProduct(product);
        return responseUtil.successResponse(new ProductResponse(productEntity));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RestAPIResponse> getProduct( @PathVariable  String id) {
        ProductEntity product = productService.getProduct(id);
        return responseUtil.successResponse(new ProductResponse(product));
    }

    @GetMapping()
    public ResponseEntity<RestAPIResponse> getProducts(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return responseUtil.successResponse(new PagingResponse(productService.getProducts(), productService.getPageProduct(pageNumber, pageSize)), "");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestAPIResponse> updateProduct(@PathVariable  String id, @RequestBody ProductRequest productRequest) {
        ProductEntity product = productService.updateProduct(id, productRequest);
        return responseUtil.successResponse(new ProductResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> deleteProduct(@PathVariable  String id) {
        return responseUtil.successResponse(productService.delProduct(id), "failure");
    }
}
