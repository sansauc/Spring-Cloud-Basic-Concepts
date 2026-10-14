package com.sansa.service_catalogo.service;

import com.sansa.service_catalogo.model.Catalog;

import java.util.List;
import java.util.Optional;

public interface CatalogService {

    List<Catalog> findAll();

    Catalog findById(long id);

    Catalog findByName(String name);

    Catalog save(Catalog catalog);
}
