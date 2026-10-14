package com.sansa.service_product.client;

import com.sansa.service_product.model.Catalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-catalogo")
public interface CatalogClient {

    @GetMapping("/api/v1/catalogs/{id}")
    Catalog findById(@PathVariable("id") Long id);


}
