package com.bookstore.spring.boot.controllers.model.request;

import com.bookstore.spring.boot.common.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest {
    private String authToken;
    private String productName;
    private String productImage;
    private double price;
    private int quantity;
    private String description;
    private String manufacture;
    private String author;
    private String categoryId;
    private Status status;
}
