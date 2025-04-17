package com.example.recipes_helper.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class IdUserProduct implements Serializable {
    
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    protected Long userId;

    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="product_id")
    protected Long productId;

    public IdUserProduct() {}

    public IdUserProduct(Long userId, Long productId) {}
}
