/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: ProductService
 */
package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * The  class for ProductService
 * <p>
 * This class is used as an intermediary between ProductsRepository and ProductController
 * </p>
 * <p>
 * This class calls the crud functionally in the Repository class and is injected in the Product Controller
 * </p>
 *
 * @author Diego Leon
 */
@Service
public class ProductService {
    private final ProductsRepository productsRepository;


    @Autowired
    public ProductService(ProductsRepository userRepository) {
        this.productsRepository = userRepository;
    }


    public Product create(Product product) throws CreationFailedException, ItemHasNonNullIdException {

        if (product.getId() != null) {
            throw new ItemHasNonNullIdException();
        }


        return productsRepository.create(product);
    }

    public Optional<Product> getById(int id) throws ItemDoesNotExistException {


        Optional<Product> product = productsRepository.getById(id);

        if (!product.isPresent()) {
            throw new ItemDoesNotExistException();
        }

        return productsRepository.getById(id);
    }

    public List<Product> getByCategoryId(int id) throws ItemDoesNotExistException {

        List<Product> ProductList = productsRepository.getByCategoryId(id);
        if (ProductList.isEmpty()) {
            throw new ItemDoesNotExistException("No Items to retrieve");
        }

        return ProductList;
    }


    public List<Product> getAll() throws ItemDoesNotExistException {

        List<Product> ProductList = productsRepository.getAll();
        if (ProductList.isEmpty()) {
            throw new ItemDoesNotExistException("No Items to retrieve");
        }

        return productsRepository.getAll();
    }

    public void delete(Product model) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {


        productsRepository.delete(model);

    }

    public void deletebyId(int id) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {

        productsRepository.deleteById(id);
    }

    public Product update(Product product) throws ItemDoesNotExistException, UpdateFailedException {

        if ((!productsRepository.getById(product.getId()).isPresent())) {
            throw new UpdateFailedException();
        }

        return productsRepository.update(product);
    }


    public List<Product> getAllFeatured() throws ItemDoesNotExistException {

        List<Product> ProductList = productsRepository.getAllFeatured();
        if (ProductList.isEmpty()) {
            throw new ItemDoesNotExistException("No Items to retrieve");
        }

        return ProductList;
    }

    // net price of product

    public Double getNetPrice(Product product) {

        if (product.getOnSale() == null) {
            return product.getPrice();
        } else {
            return ((Double) (1.00 - product.getOnSale().getDiscount())) * product.getPrice();
        }

    }
}

