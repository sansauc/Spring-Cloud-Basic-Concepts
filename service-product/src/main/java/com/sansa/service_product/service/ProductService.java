package com.sansa.service_product.service;

import com.sansa.service_product.dto.ProductDetailResponseDto;
import com.sansa.service_product.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    ProductDetailResponseDto findById(Long id);

    ProductDetailResponseDto findByName(String name);

    Product save(Product product);

}
