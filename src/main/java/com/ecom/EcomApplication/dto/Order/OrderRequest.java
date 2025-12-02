package com.ecom.EcomApplication.dto.Order;

import com.ecom.EcomApplication.Model.Order.OrderItems;
import com.ecom.EcomApplication.Model.Order.OrderStatus;
import com.ecom.EcomApplication.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private User user;
    private Double price;
    private List<OrderItems> items;
}
