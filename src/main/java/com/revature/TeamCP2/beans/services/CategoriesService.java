package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.entities.ProductCategory;

import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
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

    public ProductCategory update(ProductCategory productCategory) {
        return categoriesRepository.update(productCategory);
    }


    public ProductCategory delete(ProductCategory productCategory) {
        try {
            categoriesRepository.delete(productCategory);
        } catch (ItemHasNoIdException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteById(Integer id) {
        categoriesRepository.deleteById(id);
    }


    public List<ProductCategory> getAllUsers() {
        return categoriesRepository.getAll();
    }

    public Optional<ProductCategory> getById(int id) throws ItemDoesNotExistException {
        return categoriesRepository.getById(id);
    }
}
