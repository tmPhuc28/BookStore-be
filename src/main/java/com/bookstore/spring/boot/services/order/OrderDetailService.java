package com.bookstore.spring.boot.services.order;

import com.bookstore.spring.boot.controllers.model.request.OrderDetailRequest;
import com.bookstore.spring.boot.controllers.model.response.Obj;
import com.bookstore.spring.boot.entities.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> addOrder(List<OrderDetailRequest> orderDetails);

    List<Obj> getDetailByOrderId(String order_id);

    List<OrderDetail> getDetails();

    Page<OrderDetail> getPages(int pageNumber, int pageSize);
}
