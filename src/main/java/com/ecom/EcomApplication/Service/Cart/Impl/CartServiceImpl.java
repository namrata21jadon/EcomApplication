package com.ecom.EcomApplication.Service.Cart.Impl;

import com.ecom.EcomApplication.Handler.CartException;
import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Model.Cart.CartItem;
import com.ecom.EcomApplication.Model.Product.Product;
import com.ecom.EcomApplication.Model.User.User;
import com.ecom.EcomApplication.Repository.Cart.CartRepository;
import com.ecom.EcomApplication.Repository.Product.ProductRepository;
import com.ecom.EcomApplication.Repository.User.UserRepository;
import com.ecom.EcomApplication.Service.Cart.CartService;
import com.ecom.EcomApplication.dto.Cart.CartRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Boolean addProductToCart(Long userId, CartRequest cartRequest) {
        //Check user exist
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()){
            return false;
        }
        User user = userOpt.get();
        // Check product exist
        Optional<Product> productOpt = productRepository.findById(cartRequest.getProductId());
        if(productOpt.isEmpty()){
            return false;
        }
        // Check product quantity
        Product product = productOpt.get();
        if(product.getQuantity() < cartRequest.getQuantity()){
            return false;
        }

        //Adding to Cart
        CartItem existingCart = cartRepository.findByUserAndProduct(user,product);
        if(existingCart != null){
            existingCart.setQuantity(existingCart.getQuantity() + cartRequest.getQuantity());
            existingCart.setPrice(product.getPrice() * cartRequest.getQuantity());
            cartRepository.save(existingCart);
        }
        else{
            CartItem newCart = CartItem.builder()
                    .product(product)
                    .user(user)
                    .quantity(cartRequest.getQuantity())
                    .price(product.getPrice() * cartRequest.getQuantity())
                    .build();
            cartRepository.save(newCart);
        }
        return true;
    }

    @Override
    public boolean removeProductFromCart(String userId, Long productId) {
        //Check User Exists
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        User user = userOpt.get();
        //Check product exists
        Optional<Product> productOpt = productRepository.findById(productId);
        Product product = productOpt.get();
        if(userOpt.isPresent() || productOpt.isPresent()){
            cartRepository.deleteByUserAndProduct(user, product);
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> getCartforUser(Long userId) throws UserNotFoundException, CartException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("The user does not exist"));
        List<CartItem> cart = cartRepository.findByUser(user);
        if(cart != null){
            return cart;
        }
        throw new CartException("Cart not created Yet!!!");
    }

    @Override
    public void clearCart(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        cartRepository.deleteByUser(user);
    }
}
