package com.sansa.service_catalogo.service;

import com.sansa.service_catalogo.model.Catalog;
import com.sansa.service_catalogo.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Catalog findById(long id) {

        return catalogRepository.findById(id).orElseThrow(()-> new RuntimeException("Catalog not found with id: " + id));

    }

    @Transactional(readOnly = true)
    @Override
    public Catalog findByName(String name) {
        return catalogRepository.findByName(name).orElseThrow(()-> new RuntimeException("Catalog not found with name: " + name));
    }

    @Transactional
    @Override
    public Catalog save(Catalog catalog) {

        if(catalogRepository.existsByName(catalog.getName())){
            throw new RuntimeException("Catalog already exists with name: " + catalog.getName());
        }

        Catalog catalogSaved = new Catalog();

        catalogSaved.setName(catalog.getName());

        return catalogRepository.save(catalogSaved);
    }

}
