package com.bookstore.spring.boot.controllers.model.response;

import com.bookstore.spring.boot.common.enums.Status;
import com.bookstore.spring.boot.entities.OrderDetail;
import com.bookstore.spring.boot.entities.OrderEntity;
import com.bookstore.spring.boot.entities.ProductEntity;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;
    private String userId;
    private String receiveName;
    private String addressReceive;
    private String phoneReceive;
    private double total;

    public OrderResponse(OrderEntity order){
        this.id = order.getId();
        this.userId = order.getUserId();
        this.receiveName = order.getReceiveName();
        this.addressReceive = order.getAddressReceive();
        this.phoneReceive = order.getPhoneReceive();
//        this.status = order.
        this.total = order.getTotal();
    }
}
