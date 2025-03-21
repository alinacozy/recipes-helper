package com.example.recipes_helper.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class IdUserProduct implements Serializable {
    
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    protected Long userId;

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="product_id")
    protected Long productId;

    public IdUserProduct() {}

    public IdUserProduct(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId(){
        return this.userId;
    }

    public Long getProductId(){
        return this.productId;
    }

    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {return true;}
        if (!(otherOb instanceof IdUserProduct)) {return false;}
        IdUserProduct other = (IdUserProduct) otherOb;
        return ((userId==null ? other.userId==null :
            userId.equals(other.userId)) && (productId.equals(other.productId)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }

}
