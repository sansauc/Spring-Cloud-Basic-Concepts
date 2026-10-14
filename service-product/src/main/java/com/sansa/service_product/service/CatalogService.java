package com.sansa.service_product.service;

import com.sansa.service_product.client.CatalogClient;
import com.sansa.service_product.model.Catalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogService {

    private final CatalogClient catalogClient;

    public Catalog findById(Long catalogId) {
        return catalogClient.findById(catalogId);
    }

}
