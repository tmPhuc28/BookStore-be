package com.bookstore.spring.boot.controllers;

public interface ApiPath {
    // Base URL
    String BASE_API_URL = "/api";

    // Authenticate APIs
    String AUTHENTICATE_API = BASE_API_URL + "/auth";

    // User APIs
    String USER_API = BASE_API_URL + "/user";

    // Product APIs
    String PRODUCT_API = BASE_API_URL + "/product";

    // Category APIs
    String CATEGORY_API = BASE_API_URL + "/category";

    // Order APIs
    String ORDER_API = BASE_API_URL + "/order";

    // Order detail APIs
    String ORDER_DETAIL_API = BASE_API_URL + "/order-detail";

    // Cart item
    String CART_API = BASE_API_URL + "/cart";
}
