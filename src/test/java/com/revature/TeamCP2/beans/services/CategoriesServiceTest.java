package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.entities.ProductCategory;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import com.revature.TeamCP2.exceptions.UpdateFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoriesServiceTest {
    ProductCategory Categories1;
    ProductCategory Categories2;
    ProductCategory Categories3;

    @MockBean
    CategoriesRepository categoriesRepositoryMock;


    @BeforeEach
    public void beforeEach() {
        Categories1 = new ProductCategory("Basket ball", "Sport played with an orange ball", null);
        Categories2 = new ProductCategory("Soccer", "Sport played with a white ball", null);
        Categories3 = new ProductCategory("Base ball", "Sport played by running from base to base", null);
    }

    @Test
    public void createUserCategories(@Autowired CategoriesService categoriesService) {

        ProductCategory productCategory = new ProductCategory(null,null,null);
        when(categoriesRepositoryMock.create(productCategory)).thenReturn(productCategory);

        ProductCategory retrievedcategory = categoriesService.create(productCategory);

        assertEquals(retrievedcategory, productCategory);
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
    public void getAllUsersReturnsListOfProductCategories(@Autowired CategoriesService categoriesService) {
        List<ProductCategory> productCategoryListToReturn = new ArrayList<>();
        productCategoryListToReturn.add(Categories1);
        productCategoryListToReturn.add(Categories2);
        productCategoryListToReturn.add(Categories3);
        when(categoriesRepositoryMock.getAll()).thenReturn(productCategoryListToReturn);

        List<ProductCategory> returnedCategoryList = categoriesService.getAllUsers();

        assertEquals(productCategoryListToReturn, returnedCategoryList);
        verify(categoriesRepositoryMock, times(1)).getAll();
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

    @Test
    public void deleteByIdCallsCategoryRepo(@Autowired CategoriesService categoriesService) {
        int categoryIdToPass = 410;
        doNothing().when(categoriesRepositoryMock).deleteById(categoryIdToPass);

        categoriesService.deleteById(categoryIdToPass);

        verify(categoriesRepositoryMock, times(1)).deleteById(categoryIdToPass);
    }

    @Test
    public void deleteCallsCategoryRepoAndReturnsNull(@Autowired CategoriesService categoriesService) throws ItemHasNoIdException {
        ProductCategory productCategoryToPass = Categories1;
        doThrow(ItemHasNoIdException.class).when(categoriesRepositoryMock).delete(productCategoryToPass);

        ProductCategory returnedCategory = categoriesService.delete(productCategoryToPass);

        verify(categoriesRepositoryMock, times(1)).delete(productCategoryToPass);
        assertNull(returnedCategory);
    }
}
