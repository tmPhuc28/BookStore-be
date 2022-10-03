package com.bookstore.spring.boot.repositories;

import com.bookstore.spring.boot.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    OrderDetail getById(String id);

    OrderDetail getByProductId(String productId);

    List<OrderDetail> findByOrderId(String orderId);

    List<OrderDetail> findAllByOrderId(String orderId);
}
