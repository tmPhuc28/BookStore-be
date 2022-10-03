package com.bookstore.spring.boot.services.cart;

import com.bookstore.spring.boot.controllers.model.request.CartRequest;
import com.bookstore.spring.boot.controllers.model.response.CartResponse;
import com.bookstore.spring.boot.entities.CartItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {
    CartResponse addItem(CartRequest cart);
    CartResponse updateItem(String id, CartRequest cart);
    List<CartResponse> getItem(String id);
    List<CartResponse> getAllItem();
    List<CartItem> getItemsByUserId(String id);
    Page<CartItem> getPages(int pageNumber, int pageSize);
    CartItem remove(String id);
    CartItem removeAll();
}
