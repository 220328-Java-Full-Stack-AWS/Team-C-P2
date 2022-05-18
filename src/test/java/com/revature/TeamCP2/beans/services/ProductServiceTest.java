package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.entities.ProductCategory;
import com.revature.TeamCP2.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    Product PRODUCT;
    Product PRODUCT_1;
    Product PRODUCT_2;

    ProductCategory CATEGORY;

    @Autowired
    ProductService productService;
    @MockBean
    ProductsRepository productRepositoryMock;

    @BeforeEach
    void setUp() {
        CATEGORY = new ProductCategory("Cateory", "Test Category", null);
        PRODUCT = new Product(1, "Test Product", "Testing Product", .99, CATEGORY,
                true, null);
        PRODUCT_1 = new Product(2, "Second Product", "Testing second Product", 1.99, CATEGORY,
                true, null);
        PRODUCT_2 = new Product(3, "Third Product", "Not Featured Product", 200, CATEGORY,
                false, null);


    }

    @Test
    public void createReturnsCreatedUserOnSuccess() throws CreationFailedException, ItemHasNonNullIdException {

        Product PRODUCT_2 = PRODUCT;
        PRODUCT_2.setId(null);
        when(productRepositoryMock.create(PRODUCT_2)).thenReturn(PRODUCT);

        Product returnedProduct = productService.create(PRODUCT);

        assertEquals(returnedProduct, PRODUCT);
    }

    @Test
    public void createReturnsItemHasNonNullIdException() throws CreationFailedException, ItemHasNonNullIdException {
        assertThrows(ItemHasNonNullIdException.class,
                () -> {
                    productService.create(PRODUCT);
                });
        verify(productRepositoryMock, times(0)).create(PRODUCT);
    }


    @Test
    void getByIdReturnsOnSuccess(@Autowired ProductService productService) throws ItemDoesNotExistException {

        when(productRepositoryMock.getById(2)).thenReturn(Optional.ofNullable(PRODUCT_1));


        Product retrievedProduct = productService.getById(2).get();

        assertEquals(PRODUCT_1, retrievedProduct);

    }

    @Test
    void getByIdReturnsItemDoesNotExistException() {


        assertThrows(ItemDoesNotExistException.class,
                () -> {
                    productService.getById(PRODUCT_1.getId());
                });
        verify(productRepositoryMock, times(1)).getById(PRODUCT_1.getId());
    }


    @Test
    void getAllUserOnSuccess() throws ItemDoesNotExistException {
        List<Product> list = Arrays.asList(PRODUCT, PRODUCT_1, PRODUCT_2);

        when(productRepositoryMock.getAll()).thenReturn(list);
        assertEquals(3, productService.getAll().size());
    }

    @Test
    void getAllUserReturnsItemDoesNotExistException() throws ItemDoesNotExistException {
        assertThrows(ItemDoesNotExistException.class,
                () -> {
                    productService.getAll();
                });
        verify(productRepositoryMock, times(1)).getAll();

    }


    @Test
    void delete() throws ItemHasNoIdException {
        Product product = new Product(3, "Third Product", "Not Featured Product", 200, CATEGORY,
                false, null);

        productRepositoryMock.delete(product);
        verify(productRepositoryMock, times(1)).delete(product);
    }

    @Test
    void deletebyId() {
        Product product = new Product(3, "Third Product", "Not Featured Product", 200, CATEGORY,
                false, null);

        productRepositoryMock.deleteById(3);
        verify(productRepositoryMock, times(1)).deleteById(3);
    }


    @Test
    void update() throws ItemDoesNotExistException, UpdateFailedException {
        PRODUCT_2.setDescr("Not Featured Update");
        PRODUCT_2.setName("Third Update");
        Product update = new Product();
        update.setId(3);
        update.setName("Third Update");
        update.setDescr("Not Featured Update");
        update.setPrice(200);
        update.setCategory(CATEGORY);
        update.setIs_featured(false);
        update.setImage(null);

        when(productRepositoryMock.update(update)).thenReturn(update);

        Product updated = productService.update(update);

        assertEquals(update.getName(), updated.getName());
        verify(productRepositoryMock, times(1)).update(update);

    }

    @Test
    void updateReturnsItemDoesNotExistException() throws ItemDoesNotExistException, UpdateFailedException {

    }

    @Test
    void getAllFeatured() throws ItemDoesNotExistException {
        List<Product> list = Arrays.asList(PRODUCT_2);

        when(productRepositoryMock.getAllFeatured()).thenReturn(list);
        assertEquals(1, productService.getAllFeatured().size());
    }

    @Test
    void getAllFeaturedReturnsItemDoesNotExistException() throws ItemDoesNotExistException {
        assertThrows(ItemDoesNotExistException.class,
                () -> {
                    productService.getAllFeatured();
                });
        verify(productRepositoryMock, times(1)).getAllFeatured();

    }


}