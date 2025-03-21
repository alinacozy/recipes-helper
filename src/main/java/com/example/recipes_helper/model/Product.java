package com.example.recipes_helper.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "products", schema = "recipes-helper-db")
public class Product {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="product_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="product_id")
    private Long productId;

    @Column(name="product_name")
    private String productName;

    @Column
    private String unit;

    @Column(name="product_category")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListProduct> ListProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProduct> UserProducts;

    protected Product() {}

    public Product(Long productId, String productName, String unit, ProductCategory productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.productCategory = productCategory;
    }

    public Long getProductId() {
        return this.productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getUnit() {
        return this.unit;
    }

    public ProductCategory productCategory() {
        return this.productCategory;
    }

}
