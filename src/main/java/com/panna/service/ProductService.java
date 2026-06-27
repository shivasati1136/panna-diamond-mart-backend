package com.panna.service;

import com.panna.dto.ProductRequest;
import com.panna.entity.Product;
import com.panna.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // ADD PRODUCT
    public Product addProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // GET PRODUCTS WITH PAGINATION
    public Page<Product> getProductsWithPagination(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }
    // SEARCH PRODUCTS
    public List<Product> searchProducts(String keyword) {

        return productRepository
                .findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }
    // GET PRODUCT BY ID
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    // UPDATE PRODUCT
    public Product updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());

        return productRepository.save(product);
    }

    // DELETE PRODUCT
    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        productRepository.delete(product);

        return "Product Deleted Successfully";
    }
}