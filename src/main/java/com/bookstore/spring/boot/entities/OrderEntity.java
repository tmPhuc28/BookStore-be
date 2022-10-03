package com.bookstore.spring.boot.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity extends BaseEntity{
    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "user_id", length = 32)
    private String userId;

    @Column(name = "receive_name")
    private String receiveName;

    @Column(name = "address_receive")
    private String addressReceive;

    @Column(name = "phone_receive", length = 15)
    private String phoneReceive;

    @Column(name = "total")
    private double total;
}
