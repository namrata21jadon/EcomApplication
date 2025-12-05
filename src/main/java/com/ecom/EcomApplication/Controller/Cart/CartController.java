package com.ecom.EcomApplication.Controller.Cart;

import com.ecom.EcomApplication.Handler.CartException;
import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Model.Cart.CartItem;
import com.ecom.EcomApplication.Service.Cart.CartService;
import com.ecom.EcomApplication.dto.Cart.CartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestHeader("USER_ID") Long userId,
                                                   @RequestBody CartRequest cartRequest){
        if(!cartService.addProductToCart(userId, cartRequest)){
            return ResponseEntity.badRequest().body("Error adding product to cart either user or product does not exist or product not available of the asked quantity");
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@RequestHeader("USER_ID") String userId,
                                                        @PathVariable Long productId){
        boolean isProductRemoved = cartService.removeProductFromCart(userId, productId);
        return isProductRemoved ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartforUser(@RequestHeader("USER_ID") Long userId) throws UserNotFoundException, CartException {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartforUser(userId));
    }


}
