/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: UserService provides implementations to persist or retrieve user objects.
 * UserService @Autowires UserRepository.
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.beans.repositories.UserRepository;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
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

    public List<Product> getAllUsers() {
        return productsRepository.getAll();
    }

    public Optional<Product> getById(int id) throws ItemDoesNotExistException {
        return productsRepository.getById(id);
    }
}

