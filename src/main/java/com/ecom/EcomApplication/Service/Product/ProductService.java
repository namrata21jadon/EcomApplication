package com.ecom.EcomApplication.Service.Product;

import com.ecom.EcomApplication.Handler.ProductException;
import com.ecom.EcomApplication.dto.Product.ProductRequest;
import com.ecom.EcomApplication.dto.Product.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductResponse addProduct(ProductRequest productRequest);

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws ProductException;

    List<ProductResponse> getProducts();

    String deleteProduct(Long id) throws ProductException;

    List<ProductResponse> searchProduct(String keyword);
}
