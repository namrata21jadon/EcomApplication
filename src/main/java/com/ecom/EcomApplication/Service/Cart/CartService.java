package com.ecom.EcomApplication.Service.Cart;

import com.ecom.EcomApplication.Handler.CartException;
import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.dto.Cart.CartRequest;
import com.ecom.EcomApplication.dto.Cart.CartResponse;

public interface CartService {
    Boolean addProductToCart(Long userId, CartRequest cartRequest);

    boolean removeProductFromCart(String userId, Long productId);

    CartResponse getCartforUser(Long userId) throws UserNotFoundException, CartException;
}
