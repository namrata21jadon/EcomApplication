package com.ecom.EcomApplication.dto.Cart;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private Integer quantity;
}
