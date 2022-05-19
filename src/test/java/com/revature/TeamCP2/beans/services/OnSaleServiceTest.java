package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.OnSaleRepository;
import com.revature.TeamCP2.entities.OnSale;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OnSaleServiceTest {

    OnSale ON_SALE_1;
    OnSale ON_SALE_2;
    OnSale ON_SALE_3;
    List<OnSale> ON_SALE_LIST;

    @MockBean OnSaleRepository onSaleRepositoryMock;

    @BeforeEach
    public void beforeEach() {
        ON_SALE_1 = new OnSale(3, .40);
        ON_SALE_2 = new OnSale(4, .20);
        ON_SALE_3 = new OnSale(5, .10);

        ON_SALE_LIST = new ArrayList<>();
        ON_SALE_LIST.add(ON_SALE_1);
        ON_SALE_LIST.add(ON_SALE_2);
        ON_SALE_LIST.add(ON_SALE_3);
    }

    @Test
    public void createOnSaleCallsOnSaleRepoAndReturnsOnSale(@Autowired OnSaleService onSaleService) {
        OnSale onSaleToCreate = ON_SALE_1;
        when(onSaleRepositoryMock.create(onSaleToCreate)).thenReturn(onSaleToCreate);

        OnSale createdOnSale = onSaleService.createOnSale(onSaleToCreate);

        assertEquals(createdOnSale, onSaleToCreate);
        verify(onSaleRepositoryMock, times(1)).create(onSaleToCreate);
    }

    @Test
    public void getByIdCallsOnSaleRepoAndReturnsOptionalOnSale(@Autowired OnSaleService onSaleService) throws ItemDoesNotExistException {
        int idToGet = 9999;
        Optional<OnSale> opOnSaleToReturn = Optional.of(ON_SALE_2);
        when(onSaleRepositoryMock.getById(idToGet)).thenReturn(opOnSaleToReturn);

        Optional<OnSale> returnedOnSale = onSaleService.getById(idToGet);

        assertEquals(returnedOnSale, opOnSaleToReturn);
        verify(onSaleRepositoryMock, times(1)).getById(idToGet);
    }

    @Test
    public void getAllCallsOnSaleRepoAndReturnsList(@Autowired OnSaleService onSaleService) {
        List<OnSale> listToReturn = ON_SALE_LIST;
        when(onSaleRepositoryMock.getAll()).thenReturn(listToReturn);

        List<OnSale> returnedOnSaleList = onSaleService.getAll();

        assertEquals(listToReturn, returnedOnSaleList);
        verify(onSaleRepositoryMock, times(1)).getAll();
    }

    @Test
    public void deleteCallsOnSaleRepo(@Autowired OnSaleService onSaleService) throws ItemHasNoIdException, ItemDoesNotExistException {
        OnSale onSaleToDelete = ON_SALE_3;

        onSaleService.delete(onSaleToDelete);

        verify(onSaleRepositoryMock, times(1)).delete(onSaleToDelete);
    }

    @Test
    public void updateCallsOnSaleRepoAndReturnsOnSale(@Autowired OnSaleService onSaleService) {
        OnSale onSaleItem = ON_SALE_1;
        when(onSaleRepositoryMock.update(onSaleItem)).thenReturn(onSaleItem);

        OnSale returnedOnSaleItem = onSaleService.update(onSaleItem);

        assertEquals(onSaleItem, returnedOnSaleItem);
    }
}
