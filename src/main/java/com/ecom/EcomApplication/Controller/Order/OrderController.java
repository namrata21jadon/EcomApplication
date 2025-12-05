package com.ecom.EcomApplication.Controller.Order;

import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Service.Order.OrderService;
import com.ecom.EcomApplication.dto.Order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("USER-ID") Long userId) throws UserNotFoundException {
        OrderResponse orderResponse = orderService.createOrder(userId);
        return new ResponseEntity<>(orderResponse,HttpStatus.CREATED);
    }

}
