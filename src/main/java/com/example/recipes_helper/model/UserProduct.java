package com.example.recipes_helper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "userProduct", schema = "recipes-helper-db")
@IdClass(IdUserProduct.class)
public class UserProduct {
    @Id
    @Column(name="user_id")
    @JsonProperty("user_id")
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    @JsonProperty("product_id")
    private Long productId;

    @Column
    private Integer count;

    @ManyToOne
    @JsonIgnore
    @MapsId("productId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JsonIgnore
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    public UserProduct() {}
}
