package com.sansa.service_product.controller;

import com.sansa.service_product.dto.ProductDetailResponseDto;
import com.sansa.service_product.model.Product;
import com.sansa.service_product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponseDto> findProductById(@PathVariable Long id) {
        ProductDetailResponseDto product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDetailResponseDto> findProductByName(@PathVariable String name) {
        ProductDetailResponseDto product = productService.findByName(name);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(201).body(savedProduct);
    }

}
