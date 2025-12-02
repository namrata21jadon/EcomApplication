package com.ecom.EcomApplication.Service.Product.Impl;

import com.ecom.EcomApplication.Handler.ProductException;
import com.ecom.EcomApplication.Model.Product.Product;
import com.ecom.EcomApplication.Repository.Product.ProductRepository;
import com.ecom.EcomApplication.Service.Product.ProductService;
import com.ecom.EcomApplication.dto.Product.ProductRequest;
import com.ecom.EcomApplication.dto.Product.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = objectMapper.convertValue(productRequest, Product.class);
        productRepository.save(product);
        return objectMapper.convertValue(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws ProductException {
        return productRepository.findById(id)
                .map(existingProduct ->
                        {
                            existingProduct.setName(productRequest.getName());
                            existingProduct.setDescription(productRequest.getDescription());
                            existingProduct.setPrice(productRequest.getPrice());
                            existingProduct.setQuantity(productRequest.getQuantity());
                            existingProduct.setCategory(productRequest.getCategory());
                            return objectMapper.convertValue(existingProduct, ProductResponse.class);
                        })
                .orElseThrow(() -> new ProductException("Product Not Found"));

    }

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findByActiveTrue();
        return products.stream()
                .map( product -> objectMapper.convertValue(product, ProductResponse.class))
                .toList();
    }

    @Override
    public String deleteProduct(Long id) throws ProductException {
        productRepository.findById(id)
                .map(existingProduct ->
                        {
                            existingProduct.setActive(false);
                            return productRepository.save(existingProduct);
                        }
                        )
                .orElseThrow(() -> new ProductException("Product Not Found"));
        return "Product No Longer Available in stock";
    }

    @Override
    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchByKeyword(keyword)
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .map(product -> objectMapper.convertValue(product, ProductResponse.class))
                        .toList())
                .orElseThrow(() -> new ProductException("Product not Found"));
    }
}
