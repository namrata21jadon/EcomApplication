package com.ecom.EcomApplication.Service.Order.Impl;

import com.ecom.EcomApplication.Handler.OrderException;
import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Model.Cart.CartItem;
import com.ecom.EcomApplication.Model.Order.Order;
import com.ecom.EcomApplication.Model.Order.OrderItems;
import com.ecom.EcomApplication.Model.Order.OrderStatus;
import com.ecom.EcomApplication.Model.User.User;
import com.ecom.EcomApplication.Repository.Cart.CartRepository;
import com.ecom.EcomApplication.Repository.Order.OrderRepository;
import com.ecom.EcomApplication.Repository.User.UserRepository;
import com.ecom.EcomApplication.Service.Cart.CartService;
import com.ecom.EcomApplication.Service.Order.OrderService;
import com.ecom.EcomApplication.dto.Order.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;
    private final ObjectMapper objectMapper;

    @Override
    public OrderResponse createOrder(Long userId) throws OrderException, UserNotFoundException {
        //Validate for cart items
        List<CartItem> cart = cartService.getCartforUser(userId);
        if(cart.isEmpty()){
            throw new OrderException("Cart not created Yet!!!");
        }
        //validate user
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found!!!"));
        //Calculate total price
        Double totalPrice = cart.stream()
                .map(CartItem::getPrice)
                .reduce(0.0, Double::sum);
        //Create Order

        Order order = Order.builder()
                .user(user)
                .totalPrice(totalPrice)
                .status(OrderStatus.CONFIRMED)
                .build();

        List<OrderItems> orderItems = cart.stream()
                .map(item -> OrderItems.builder()
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .order(order)
                        .build()
                ).toList();

        order.setItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        //Empty Cart

        cartService.clearCart(userId);
        return objectMapper.convertValue(savedOrder, OrderResponse.class);
    }
}
