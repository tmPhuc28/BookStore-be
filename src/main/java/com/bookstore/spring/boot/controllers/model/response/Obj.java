package com.bookstore.spring.boot.controllers.model.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Obj {
    private String id;
    private String name;
    private String image;
    private double price;
    private int quantity;
}
