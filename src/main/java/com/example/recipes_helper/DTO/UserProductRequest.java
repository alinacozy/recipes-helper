package com.example.recipes_helper.DTO;

import lombok.Data;

@Data
public class UserProductRequest {
    private Long productId;
    private Integer count;
}