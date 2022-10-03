package com.bookstore.spring.boot.services.product;

import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.ProductRequest;
import com.bookstore.spring.boot.entities.Authenticated;
import com.bookstore.spring.boot.entities.ProductEntity;
import com.bookstore.spring.boot.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final AuthenticatedRepository authenticatedRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, AuthenticatedRepository auth) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.authenticatedRepository = auth;
    }

    @Override
    public ProductEntity createProduct(ProductRequest productRequest) {
        if(!authenticatedRepository.existsById(productRequest.getAuthToken())){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "User id not null");
        }

        Authenticated auth = authenticatedRepository.getById(productRequest.getAuthToken());
        ProductEntity product = new ProductEntity();

        if (productRequest.getProductName() == null || productRequest.getProductName().isEmpty()) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product Name is null");
        }

        if (productRequest.getPrice() < 0) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product price is always bigger or equal than 0 ");
        }

        if (productRequest.getQuantity() < 0) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product price is always bigger or equal than 0 ");
        }

        if ((productRequest.getCategoryId().isEmpty() || productRequest.getCategoryId() == null) || !categoryRepository.existsById(productRequest.getCategoryId())) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product category is null");
        }

        if ((productRequest.getAuthor().isEmpty() || productRequest.getAuthor() == null)) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product author is null or not exists");
        }

        if (productRequest.getManufacture().isEmpty() || productRequest.getManufacture() == null) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product manufacture is null");
        }

        product.setId(UniqueID.getUUID());
        product.setUserId(auth.getUserId());
        product.setProductName(productRequest.getProductName());
        product.setProductImage(productRequest.getProductImage());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setStatus(productRequest.getStatus());
        product.setCategoryId(productRequest.getCategoryId());
        product.setAuthor(productRequest.getAuthor());
        product.setManufacture(productRequest.getManufacture());
        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(String productId, ProductRequest productRequest) {
        if(!authenticatedRepository.existsById(productRequest.getAuthToken())){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "User id not null");
        }

        Authenticated auth = authenticatedRepository.getById(productRequest.getAuthToken());
        ProductEntity product = productRepository.getById(productId);

        if (productRequest.getProductName() == null || productRequest.getProductName().isEmpty()) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product Name is null");
        }

        if (productRequest.getPrice() < 0) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product price is always bigger or equal than 0 ");
        }

        if (productRequest.getQuantity() < 0) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product price is always bigger or equal than 0 ");
        }

        if ((productRequest.getCategoryId().isEmpty() || productRequest.getCategoryId() == null) || !categoryRepository.existsById(productRequest.getCategoryId())) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product category is null");
        }

        if ((productRequest.getAuthor().isEmpty() || productRequest.getAuthor() == null)) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product author is null or not exists");
        }

        if (productRequest.getManufacture().isEmpty() || productRequest.getManufacture() == null) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST, "Product manufacture is null");
        }

        product.getId();
        product.setUserId(auth.getUserId());
        product.setProductName(productRequest.getProductName());
        product.setProductImage(productRequest.getProductImage());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setStatus(productRequest.getStatus());
        product.setCategoryId(productRequest.getCategoryId());
        product.setAuthor(productRequest.getAuthor());
        product.setManufacture(productRequest.getManufacture());


        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<ProductEntity> getPageProduct(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return productRepository.findAll(pageRequest);
    }

    @Override
    public ProductEntity getProduct(String productId) {
        return productRepository.getById(productId);
    }

    @Override
    public Boolean delProduct(String productId) {
        ProductEntity product = productRepository.getById(productId);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }
}
