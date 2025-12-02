package com.ecom.EcomApplication.dto.Product;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private String category;
    private Integer quantity;
    private String image;
}
