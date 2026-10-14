package com.sansa.service_product.service;

import com.sansa.service_product.dto.ProductDetailResponseDto;
import com.sansa.service_product.model.Catalog;
import com.sansa.service_product.model.Product;
import com.sansa.service_product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CatalogService catalogService;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        log.info("Find all products");
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDetailResponseDto findById(Long id) {

        log.info("Find product by id: {}", id);

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        Catalog catalog = catalogService.findById(product.getCatalogId());
        return new ProductDetailResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                catalog
        );
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDetailResponseDto findByName(String name) {
        log.info("Find product by name: {}", name);
        Product product = productRepository.findByName(name).orElseThrow(() -> new RuntimeException("Product not found"));
        Catalog catalog = catalogService.findById(product.getCatalogId());

        return new ProductDetailResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                catalog
        );
    }

    @Transactional
    @Override
    public Product save(Product product) {
        log.info("Saving product: {}", product);

        Product savedProduct = new Product();
        savedProduct.setName(product.getName());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setCatalogId(product.getCatalogId());

        return productRepository.save(savedProduct);
    }
}
