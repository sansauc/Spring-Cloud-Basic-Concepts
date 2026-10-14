package com.sansa.service_catalogo.controller;

import com.sansa.service_catalogo.model.Catalog;
import com.sansa.service_catalogo.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<Catalog>> getAllCatalogs() {
        List<Catalog> catalogs = catalogService.findAll();
        return ResponseEntity.ok(catalogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getCatalogById(@PathVariable String id) {
        Catalog catalog = catalogService.findById(Long.parseLong(id));
        return ResponseEntity.ok(catalog);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Catalog> getCatalogByName(@PathVariable String name) {
        Catalog catalog = catalogService.findByName(name);
        return ResponseEntity.ok(catalog);
    }

    @PostMapping
    public ResponseEntity<Catalog> saveCatalog(@RequestBody Catalog catalog) {

        Catalog catalogSaved = catalogService.save(catalog);

        return ResponseEntity.status(HttpStatus.CREATED).body(catalogSaved);

    }



}
