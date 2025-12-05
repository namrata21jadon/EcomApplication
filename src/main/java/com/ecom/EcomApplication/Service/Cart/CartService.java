package com.ecom.EcomApplication.Service.Cart;

import com.ecom.EcomApplication.Handler.CartException;
import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Model.Cart.CartItem;
import com.ecom.EcomApplication.dto.Cart.CartRequest;

import java.util.List;

public interface CartService {
    Boolean addProductToCart(Long userId, CartRequest cartRequest);

    boolean removeProductFromCart(String userId, Long productId);

    List<CartItem> getCartforUser(Long userId) throws UserNotFoundException, CartException;

    public void clearCart(Long userId) throws CartException, UserNotFoundException;
}
