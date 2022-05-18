package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CartItemRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartItemServiceTest {

    List<CartItem> CART_ITEM_LIST;
    CartItem CART_ITEM_1;
    CartItem CART_ITEM_2;
    CartItem CART_ITEM_3;

    @MockBean
    CartItemRepository cartItemRepositoryMock;

    @BeforeEach
    public void beforeEach() {
        CART_ITEM_1 = new CartItem(new Cart(), new Product(), 2);
        CART_ITEM_2 = new CartItem(new Cart(), new Product(), 1);
        CART_ITEM_3 = new CartItem(new Cart(), new Product(), 4);

        CART_ITEM_LIST = new ArrayList<CartItem>();
        CART_ITEM_LIST.add(CART_ITEM_1);
        CART_ITEM_LIST.add(CART_ITEM_2);
        CART_ITEM_LIST.add(CART_ITEM_3);
    }

    @Test
    public void createCartItemCallsCartItemRepoAndReturnsCartItem(@Autowired CartItemService cartItemService) {
        CartItem cartItemToCreate = CART_ITEM_2;
        when(cartItemRepositoryMock.create(cartItemToCreate)).thenReturn(cartItemToCreate);

        CartItem cartItem = cartItemService.createCartItem(cartItemToCreate);

        assertEquals(cartItemToCreate, cartItem);
        verify(cartItemRepositoryMock, times(1)).create(cartItemToCreate);
    }

    @Test
    public void getByIdCallsCartRepoAndReturnsCartItemOptional(@Autowired CartItemService cartItemService) throws ItemDoesNotExistException {
        int id = 3;
        CartItem cartItemToReturn = CART_ITEM_2;
        when(cartItemRepositoryMock.getById(id)).thenReturn(Optional.of(cartItemToReturn));

        Optional<CartItem> retrievedCartItem = cartItemService.getById(id);

        assertEquals(retrievedCartItem.get(), cartItemToReturn);
        verify(cartItemRepositoryMock, times(1)).getById(id);
    }

    @Test
    public void getAllCallsCartItemRepoAndReturnsList(@Autowired CartItemService cartItemService) {

        when(cartItemRepositoryMock.getAll()).thenReturn(CART_ITEM_LIST);

        List<CartItem> retrievedList = cartItemService.getAll();

        assertEquals(CART_ITEM_LIST, retrievedList);
        verify(cartItemRepositoryMock, times(1)).getAll();
    }

    @Test
    public void getAllCartItemsByCartCallsCartItemRepoAndReturnsList(@Autowired CartItemService cartItemService) {
        Cart cart = new Cart(new User(), 45.55);
        when(cartItemRepositoryMock.getAllCartItemsByCart(cart)).thenReturn(CART_ITEM_LIST);

        List<CartItem> retrievedCartItemList = cartItemService.getAllCartItemsByCart(cart);

        assertEquals(CART_ITEM_LIST, retrievedCartItemList);
        verify(cartItemRepositoryMock, times(1)).getAllCartItemsByCart(cart);
    }

    @Test
    public void deleteCallsCartItemRepo(@Autowired CartItemService cartItemService) throws ItemDoesNotExistException, ItemHasNoIdException {
        CartItem cartItemToDelete = CART_ITEM_1;

        cartItemService.delete(cartItemToDelete);

        verify(cartItemRepositoryMock, times(1)).delete(cartItemToDelete);
    }

    @Test
    public void updateCallsCartItemRepoAndReturnsCartItem(@Autowired CartItemService cartItemService) {
        CartItem cartItemToUpdate = CART_ITEM_3;
        when(cartItemRepositoryMock.update(cartItemToUpdate)).thenReturn(cartItemToUpdate);

        CartItem updatedCartItem = cartItemService.update(cartItemToUpdate);

        assertEquals(cartItemToUpdate, updatedCartItem);
        verify(cartItemRepositoryMock, times(1)).update(cartItemToUpdate);
    }
}
