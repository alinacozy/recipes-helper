package com.example.recipes_helper.controller;

import com.example.recipes_helper.DTO.ProductDTO;
import com.example.recipes_helper.DTO.UserProductRequest;
import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.services.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductContoller {

    @Autowired
    private final ProductService service;

    @GetMapping("/all")
    public List<Product> findAllProducts (){
        return service.findAllProducts();
    }

    @PostMapping("")
    public UserProduct saveProduct (@AuthenticationPrincipal MyUserDetails userDetails, @ModelAttribute UserProductRequest product){
        UserProduct userProduct=new UserProduct();
        userProduct.setProductId(product.getProductId());
        userProduct.setUserId(userDetails.getId());
        userProduct.setCount(product.getCount());

        return service.saveProduct(userProduct);
    }

    @GetMapping("")
    public List<ProductDTO> findAllProductsByUser (@AuthenticationPrincipal MyUserDetails userDetails){
        return service.findAllProductsByUser(userDetails.getId());

    }

    // @PutMapping("/{userId}")
    // public UserProduct updateProduct (@RequestBody UserProduct product){
    //     return service.updateProduct(product);
    // }


}
