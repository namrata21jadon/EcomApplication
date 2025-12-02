package com.ecom.EcomApplication.dto.Cart;

import com.ecom.EcomApplication.Model.Product.Product;
import com.ecom.EcomApplication.Model.User.User;
import lombok.Data;

@Data
public class CartResponse {
    private Long id;
    private User user;
    private Product product;
    private Integer quantity;
    private Double price;
}
