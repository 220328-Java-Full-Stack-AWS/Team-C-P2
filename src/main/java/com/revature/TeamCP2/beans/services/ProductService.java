/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: ProductService
 */
package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository userRepository) {
        this.productsRepository = userRepository;
    }


    public Product create(Product product) {
        return productsRepository.create(product);
    }

    public Optional<Product> getById(int id) throws ItemDoesNotExistException {
        return productsRepository.getById(id);
    }

    public List<Product> getAll() {
        return productsRepository.getAll();
    }

    public void delete(Product model) throws ItemHasNoIdException {
        productsRepository.delete(model);
    }

    public void deletebyId(int id) throws ItemHasNoIdException {
        productsRepository.deleteById(id);
    }

    public Product update(Product product) {
        return productsRepository.update(product);
    }


    public List<Product> getAllFeatured() {
        return productsRepository.getAllFeatured();
    }
}

