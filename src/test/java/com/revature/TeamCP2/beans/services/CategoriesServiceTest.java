package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.controllers.CategoriesController;
import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.beans.services.*;
import com.revature.TeamCP2.entities.ProductCategory;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import com.revature.TeamCP2.exceptions.UpdateFailedException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = com.revature.TeamCP2.beans.services.CategoriesService.class)
@ExtendWith(MockitoExtension.class)
public class CategoriesServiceTest {
    ProductCategory Categories1;
    ProductCategory Categories2;

    @MockBean
    CategoriesRepository categoriesRepositoryMock;
    @MockBean
    BCryptHash bCryptHashMock;
    @MockBean
    AuthService authServiceMock;
    @MockBean
    ProductCategory productCategoryMock;

    @BeforeEach
    public void beforeEach() {
        Categories1 = new ProductCategory();


    }

    @Test
    public void createUserCategories(@Autowired CategoriesService categoriesService) {

        ProductCategory productCategory = new ProductCategory(null,null,null);
        when(categoriesRepositoryMock.create(productCategory)).thenReturn(productCategory);

        ProductCategory retrievedcategory = categoriesService.create(productCategory);

        assertEquals(retrievedcategory.getDescription(), productCategory);
        verify(categoriesRepositoryMock, Mockito.times(1)).create(productCategory);
    }
    @Test
    public void updateCategories(@Autowired CategoriesService categoriesService) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {

        ProductCategory productCategory = new ProductCategory(null, "Candy Cane", null);
        when(categoriesRepositoryMock.update(productCategory)).thenReturn(productCategory);

        ProductCategory updatedCategory = categoriesService.update(productCategory);

        assertEquals(productCategory, updatedCategory);
        verify(categoriesRepositoryMock, Mockito.times(1)).update(productCategory);
    }
    @Test
    public void getAllCategories(@Autowired CategoriesService categoriesService) {
        List<ProductCategory> listToReturn = new ArrayList<>();
        listToReturn.add(Categories1);
        listToReturn.add(Categories2);
        listToReturn.add(new ProductCategory());
        when(categoriesRepositoryMock.getAll()).thenReturn(listToReturn);

        List<ProductCategory> userList = categoriesService.getAllUsers();

        assertEquals(listToReturn, userList);
        verify(categoriesRepositoryMock, Mockito.times(1)).getAll();
    }
    @Test
    public void getByIdCategories(@Autowired CategoriesService categoriesService) throws ItemDoesNotExistException {
        int id = 2;
        Optional<ProductCategory> categoriesToReturn = Optional.of(Categories2);
        when(categoriesRepositoryMock.getById(id)).thenReturn(categoriesToReturn);

        Optional<ProductCategory> opRetrievedUser = categoriesService.getById(id);

        assertEquals(categoriesToReturn, opRetrievedUser);
        verify(categoriesRepositoryMock, Mockito.times(1)).getById(id);
    }
}
