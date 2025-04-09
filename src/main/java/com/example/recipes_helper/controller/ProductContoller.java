package com.example.recipes_helper.controller;

import com.example.recipes_helper.model.User;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductContoller {
    private final ProductService service;

    @PostMapping("/{userId}")
    public UserProduct saveProduct (@RequestBody UserProduct product){
        return service.saveProduct(product);
    }
    @GetMapping("/{userId}")
    public List<UserProduct> findAllProductsByUser (@PathVariable Long userId){
        return service.findAllProductsByUser(userId);
    }
    @PutMapping("/{userId}")
    public UserProduct updateProduct (@RequestBody UserProduct product){
        return service.updateProduct(product);
    }


}
