package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CartRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.interfaces.Role;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @MockBean
    CartRepository cartRepositoryMock;

    Cart testCart;
    User testUser;

    @BeforeEach
    public void beforeEach() {
        testUser = new User(1, "us3rn4m3", "p4ssw0rd", "Firstname", "Lastname", "email@email.com", Role.USER, null, null, null, null, null, null, null);
        testCart = new Cart(testUser,99.99 );
    }

    //test that cart gets created
    @Test
    public void cartGetsReturnedAfterCreation(@Autowired CartService cartService) {
        assertEquals(99.99, testCart.getTotal());
    }

    //test that cart gets assigned to user
    @Test
    public void cartGetsAssignedToUser(@Autowired CartService cartService) {
        assertEquals("us3rn4m3", testCart.getUser().getUsername());
    }

    //test that item gets added to cart


    //test that item gets removed from cart


    //test that item in cart gets updated


    //test that all items list is not empty


    //test that get cart by id returns correct cart


    //test that cart gets deleted


    //test that cart gets updated


}
