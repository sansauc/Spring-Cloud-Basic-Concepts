package com.sansa.service_product.dto;

import com.sansa.service_product.model.Catalog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Catalog catalog;

}
