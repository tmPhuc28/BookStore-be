package com.bookstore.spring.boot.entities;

import com.bookstore.spring.boot.common.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity extends BaseEntity{
    @Id
    @Column(name = "id", updatable = false, length = 64)
    private String id;

    @Column(name = "user_id", length = 32)
    private String userId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacture")
    private String manufacture;

    @Column(name = "author")
    private String author;

    @Column(name = "status")
    private Status status;

    @Column(name = "category_id", length = 32,  nullable = false)
    private String categoryId;
}
