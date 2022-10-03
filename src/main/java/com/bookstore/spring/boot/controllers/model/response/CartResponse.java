package com.bookstore.spring.boot.controllers.model.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String id;
    private String userId;
    private String productName;
    private String productImage;
    private double price;
    private int quantity;
}
