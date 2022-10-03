package com.bookstore.spring.boot.entities;

import com.bookstore.spring.boot.common.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@ToString
@JsonInclude(Include.NON_NULL)
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
    @Id
    @Column(name = "id", updatable = false, length = 32)
    private String id;

    @Column(name = "user_id", length = 32)
    private String userId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "status")
    private Status status;
}
