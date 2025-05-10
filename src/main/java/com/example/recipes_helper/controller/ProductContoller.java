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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductContoller {

    @Autowired
    private final ProductService service;

    @GetMapping("/all")
    @ResponseBody
    public List<Product> findAllProducts (){
        return service.findAllProducts();
    }

    @PostMapping("")
    public String saveProduct (@AuthenticationPrincipal MyUserDetails userDetails, 
    @ModelAttribute UserProductRequest product,
    RedirectAttributes redirectAttributes){
        UserProduct userProduct=new UserProduct();
        userProduct.setProductId(product.getProductId());
        userProduct.setUserId(userDetails.getId());
        userProduct.setCount(product.getCount());
        service.saveProduct(userProduct);

        redirectAttributes.addFlashAttribute("successMessage", "Вы успешно добавили продукт " + service.getProductById(product.getProductId()).getProductName());
        return "redirect:/products";
    }

    @GetMapping("")
    public String findAllProductsByUser (@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        List<ProductDTO> userProducts = service.findAllProductsByUser(userDetails.getId());
        model.addAttribute("user_products", userProducts);
        List<Product> products = service.findAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("UserProductRequest", new UserProductRequest());
        return "products";
    }

    // @PutMapping("/{userId}")
    // public UserProduct updateProduct (@RequestBody UserProduct product){
    //     return service.updateProduct(product);
    // }


}
