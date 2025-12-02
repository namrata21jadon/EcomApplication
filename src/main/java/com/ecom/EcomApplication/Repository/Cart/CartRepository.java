package com.ecom.EcomApplication.Repository.Cart;

import com.ecom.EcomApplication.Model.Cart.CartItem;
import com.ecom.EcomApplication.Model.Product.Product;
import com.ecom.EcomApplication.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);

    CartItem findByUser(User user);
}
