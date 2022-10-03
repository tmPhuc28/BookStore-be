package com.bookstore.spring.boot.services.cart;

import com.bookstore.spring.boot.common.exceptions.ApplicationException;
import com.bookstore.spring.boot.common.utils.RestAPIStatus;
import com.bookstore.spring.boot.common.utils.UniqueID;
import com.bookstore.spring.boot.controllers.model.request.CartRequest;
import com.bookstore.spring.boot.controllers.model.response.CartResponse;
import com.bookstore.spring.boot.entities.Authenticated;
import com.bookstore.spring.boot.entities.CartItem;
import com.bookstore.spring.boot.entities.ProductEntity;
import com.bookstore.spring.boot.entities.UserEntity;
import com.bookstore.spring.boot.repositories.AuthenticatedRepository;
import com.bookstore.spring.boot.repositories.CartRepository;
import com.bookstore.spring.boot.repositories.ProductRepository;
import com.bookstore.spring.boot.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    final AuthenticatedRepository authenticatedRepository;
    final UserRepository userRepository;
    final ProductRepository productRepository;
    final CartRepository cartRepository;

    public CartServiceImpl(AuthenticatedRepository authenticatedRepository, UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.authenticatedRepository = authenticatedRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartResponse addItem(CartRequest cart) {
        if(!authenticatedRepository.existsById(cart.getAuthToken())){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }

        if (!productRepository.existsById(cart.getProductId()) || cart.getProductId().isEmpty()) {
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }

        Authenticated auth = authenticatedRepository.getById(cart.getAuthToken());
        UserEntity user = userRepository.getById(auth.getUserId());
        ProductEntity product = productRepository.getById(cart.getProductId());
        CartResponse cartResponse = new CartResponse();

        if (cartRepository.existsByProductId(cart.getProductId())) {
            CartItem item = cartRepository.getByProductId(cart.getProductId());
            item.setQuantity(item.getQuantity() + cart.getQuantity());

            cartRepository.save(item);

            cartResponse.setId(item.getId());
            cartResponse.setUserId(user.getId());
            cartResponse.setProductName(product.getProductName());
            cartResponse.setPrice(product.getPrice());
            cartResponse.setProductImage(product.getProductImage());
            cartResponse.setQuantity(item.getQuantity());

            return cartResponse;
        }

        CartItem item = new CartItem();
        item.setId(UniqueID.getUUID());
        item.setUserId(user.getId());
        item.setProductId(cart.getProductId());
        item.setQuantity(cart.getQuantity());

        cartRepository.save(item);

        cartResponse.setId(item.getId());
        cartResponse.setUserId(user.getId());
        cartResponse.setProductName(product.getProductName());
        cartResponse.setProductImage(product.getProductImage());
        cartResponse.setPrice(product.getPrice());
        cartResponse.setQuantity(cart.getQuantity());

        return cartResponse;
    }

    @Override
    public CartResponse updateItem(String id, CartRequest cart) {
        if(!authenticatedRepository.existsById(cart.getAuthToken())){
            throw new ApplicationException(RestAPIStatus.BAD_REQUEST);
        }

        Authenticated auth = authenticatedRepository.getById(cart.getAuthToken());
        UserEntity user = userRepository.getById(auth.getUserId());
        CartResponse cartResponse = new CartResponse();

        CartItem item = cartRepository.getById(id);
        ProductEntity product = productRepository.getById(cart.getProductId());
        item.setQuantity(item.getQuantity() + cart.getQuantity());
        cartRepository.save(item);

        cartResponse.setId(item.getId());
        cartResponse.setUserId(user.getId());
        cartResponse.setProductName(product.getProductName());
        cartResponse.setProductImage(product.getProductImage());
        cartResponse.setPrice(product.getPrice());
        cartResponse.setQuantity(item.getQuantity());

        return cartResponse;
    }

    @Override
    public List<CartResponse> getItem(String id) {
        List<CartResponse> cartItems = new ArrayList<>();
        List<CartItem> items = cartRepository.findAllById(id);
        for (CartItem i : items) {
            CartResponse item = new CartResponse();
            ProductEntity product = productRepository.getById(i.getProductId());
            item.setId(i.getId());
            item.setUserId(i.getUserId());
            item.setProductName(product.getProductName());
            item.setProductImage(product.getProductImage());
            item.setPrice(product.getPrice());
            item.setQuantity(i.getQuantity());

            cartItems.add(item);
        }
        return cartItems;
    }

    @Override
    public List<CartResponse> getAllItem() {
        List<CartResponse> cartItems = new ArrayList<>();
        List<CartItem> items = cartRepository.findAll();
        for (CartItem i : items) {
            CartResponse item = new CartResponse();
            ProductEntity product = productRepository.getById(i.getProductId());
            item.setId(i.getId());
            item.setUserId(i.getUserId());
            item.setProductName(product.getProductName());
            item.setProductImage(product.getProductImage());
            item.setPrice(product.getPrice());
            item.setQuantity(i.getQuantity());

            cartItems.add(item);
        }
        return cartItems;
    }

    @Override
    public List<CartItem> getItemsByUserId(String id) {
        System.out.println(cartRepository.findAllByUserId(id));
        return cartRepository.findAllByUserId(id);
    }

    @Override
    public Page<CartItem> getPages(int pageNumber, int pageSize) {
        PageRequest page = PageRequest.of(pageNumber - 1, pageSize);
        return cartRepository.findAll(page);
    }

    @Override
    public CartItem remove(String id) {
        CartItem item = cartRepository.getById(id);
        cartRepository.delete(item);
        return null;
    }

    @Override
    public CartItem removeAll() {
        cartRepository.deleteAll();
        return null;
    }
}
