package com.bookstore.spring.boot.services.order;

import com.bookstore.spring.boot.controllers.model.request.OrderRequest;
import com.bookstore.spring.boot.controllers.model.response.OrderResponse;
import com.bookstore.spring.boot.entities.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    List<OrderEntity> getOrdersByUserId(String userId);

    List<OrderEntity> getAllOrders();

    Page<OrderEntity> getOrderPages(int pageNumber, int pageSize);

    OrderEntity createOrder(OrderRequest order);

    OrderEntity updateOrder( String id, OrderRequest order);

    OrderEntity updateTotal( String id);

    OrderEntity del(String id);

    OrderEntity getOrder(String id);
}
