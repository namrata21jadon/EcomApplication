package com.ecom.EcomApplication.Repository.Product;

import com.ecom.EcomApplication.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM product p WHERE p.active=true  " +
            "AND p.quantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%') ) ")
    Optional<List<Product>> searchByKeyword(@Param("keyword") String keyword);
}
