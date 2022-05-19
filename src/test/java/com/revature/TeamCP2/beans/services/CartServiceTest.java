package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CartRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.entities.User;

import com.revature.TeamCP2.interfaces.Role;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @MockBean
    CartRepository cartRepositoryMock;



    List<CartItem> CART_LIST;
    Cart CART_1;
    Cart CART_2;
    User USER_1;
    User testUser;
    Product testProduct;
    CartItem CART_ITEM_1;
    Cart testCart;

    @BeforeEach
    public void beforeEach() {

        USER_1 = new User(1, "us3rn4m3", "p4ssw0rd", "Firstname", "Lastname", "email@email.com", Role.USER, null, null, null, null, null, null, null);
        CART_1 = new Cart(USER_1, 99.99);
        CART_2 = new Cart(USER_1, 99.99);

        testCart = new Cart(testUser,99.99 );
        CART_LIST = new ArrayList<>();
        testProduct = new Product(1, "testProduct","Test Description", 10.00, null, true, null);
        CART_ITEM_1 = new CartItem(CART_1, testProduct, 9);
    }

    //test that cart gets created
    @Test
    public void cartGetsReturnedAfterCreation(@Autowired CartService cartService) {

        Cart cartToCreate = CART_2;

        when(cartRepositoryMock.create(cartToCreate)).thenReturn(cartToCreate);
        Cart cart = cartService.createCart(USER_1);


        assertEquals(cartToCreate, cart);

    }

    //test that get cart by id returns cart
    @Test
    public void getByIdReturnsCart(@Autowired CartService cartService) {
        Cart cartToGet = CART_1;

        when(cartRepositoryMock.getById(1)).thenReturn(Optional.ofNullable(cartToGet));
        Optional<Cart> cart = cartService.getCartbyId(1);

        assertEquals(Optional.ofNullable(cartToGet), cart);
    }

    //test that cart gets deleted
    @Test
    public void deleteCartCallsRepo(@Autowired CartService cartService) {
        Cart cartToDelete = CART_1;

        cartService.deleteCart(cartToDelete);
        verify(cartRepositoryMock, times(1)).delete(cartToDelete);
    }
    //test that cart gets updated
    @Test
    public void updateCallsRepoAndReturnsCart(@Autowired CartService cartService) {
        Cart cartToUpdate = CART_1;
        when(cartRepositoryMock.update(cartToUpdate)).thenReturn(cartToUpdate);

        Cart updatedCart = cartService.updateCart(cartToUpdate);
        assertEquals(cartToUpdate, updatedCart);
        verify(cartRepositoryMock, times(1)).update(cartToUpdate);

    }


}
