package com.panna.controller;

import com.panna.dto.ProductRequest;
import com.panna.entity.Product;
import com.panna.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    // =========================
    // ADD PRODUCT
    // ONLY ADMIN
    // =========================
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product addProduct(
            @Valid @RequestBody ProductRequest request
    ) {

        return productService.addProduct(request);
    }

    // =========================
    // GET ALL PRODUCTS
    // PUBLIC
    // =========================
    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    // =========================
// PAGINATION API
// PUBLIC
// =========================
    @GetMapping("/pagination")
    public Page<Product> getProductsWithPagination(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size
    ) {

        return productService
                .getProductsWithPagination(page, size);
    }

    // =========================
// SEARCH PRODUCTS
// PUBLIC
// =========================
    @GetMapping("/search")
    public List<Product> searchProducts(

            @RequestParam String keyword
    ) {

        return productService.searchProducts(keyword);
    }
    // =========================
    // GET PRODUCT BY ID
    // PUBLIC
    // =========================
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }

    // =========================
    // UPDATE PRODUCT
    // ONLY ADMIN
    // =========================
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(

            @PathVariable Long id,

            @Valid @RequestBody ProductRequest request
    ) {

        return productService.updateProduct(id, request);
    }

    // =========================
    // DELETE PRODUCT
    // ONLY ADMIN
    // =========================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {

        return productService.deleteProduct(id);
    }
    @GetMapping("/category/{category}")
    public List<Product> getRelatedProducts(
            @PathVariable String category
    ) {

        return productService.getRelatedProducts(category);

    }
}