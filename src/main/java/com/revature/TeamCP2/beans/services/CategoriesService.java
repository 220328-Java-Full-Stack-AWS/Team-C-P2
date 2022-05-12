package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.entities.ProductCategory;

import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository userRepository) {
        this.categoriesRepository = userRepository;
    }

    public ProductCategory create(ProductCategory productCategory) {
        return categoriesRepository.create(productCategory);
    }

    public List<ProductCategory> getAllUsers() {
        return categoriesRepository.getAll();
    }

    public Optional<ProductCategory> getById(int id) throws ItemDoesNotExistException {
        return categoriesRepository.getById(id);
    }
}
