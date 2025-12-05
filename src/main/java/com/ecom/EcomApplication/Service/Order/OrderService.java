package com.ecom.EcomApplication.Service.Order;

import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.dto.Order.OrderResponse;

public interface OrderService {
    public OrderResponse createOrder(Long userId) throws UserNotFoundException;
}
