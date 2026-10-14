package com.sansa.service_catalogo.repository;

import com.sansa.service_catalogo.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByName(String name);

    List<Catalog> findAllCatalogById(Long id);

    Boolean existsByName(String name);
}
