package com.ecom.EcomApplication.dto.Order;

import com.ecom.EcomApplication.Model.Order.OrderStatus;
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
    private Double price;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private LocalDate creationDate;
}
