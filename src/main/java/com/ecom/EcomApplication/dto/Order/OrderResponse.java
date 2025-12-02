package com.ecom.EcomApplication.dto.Order;

import com.ecom.EcomApplication.Model.Order.OrderItems;
import com.ecom.EcomApplication.Model.Order.OrderStatus;
import com.ecom.EcomApplication.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private User user;
    private Double price;
    private OrderStatus status;
    private List<OrderItems> items;
    private LocalDate creationDate;
    private LocalDate updateDate;
}
