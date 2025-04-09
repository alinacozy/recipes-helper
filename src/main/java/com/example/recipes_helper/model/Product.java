package com.example.recipes_helper.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table (name = "products", schema = "recipes-helper-db")
public class Product {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="product_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="pk_sequence")
    @Column(name="product_id", columnDefinition = "serial")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column
    private String unit;

    @Column(name="product_category")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ListProduct> listProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserProduct> userProducts;
    protected Product() {}
}
