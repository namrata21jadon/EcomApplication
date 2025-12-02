package com.ecom.EcomApplication.Controller.Product;

import com.ecom.EcomApplication.Handler.ProductException;
import com.ecom.EcomApplication.Service.Product.ProductService;
import com.ecom.EcomApplication.dto.Product.ProductRequest;
import com.ecom.EcomApplication.dto.Product.ProductResponse;
import com.ecom.EcomApplication.dto.User.UserResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest  productRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProduct(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)throws ProductException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword){
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProduct(keyword));
    }
}
