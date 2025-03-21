package com.example.recipes_helper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "userProduct", schema = "recipes-helper-db")
@IdClass(IdUserProduct.class)
public class UserProduct {
    @Id
    @Column(name="user_id")
    private Long userId;

    @Id
    @Column(name="product_id")
    private Long productId;

    @Column
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected UserProduct() {}

    public UserProduct(Long userId, Long productId, Integer count) {
        this.userId = userId;
        this.productId = productId;
        this.count = count;
    }

    public Long getUserId(){
        return this.userId;
    }

    public Long getProductId(){
        return this.productId;
    }

    public Integer getCount(){
        return this.count;
    }

}
