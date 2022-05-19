package com.revature.TeamCP2.beans.services;


import com.revature.TeamCP2.beans.repositories.CartRepository;
import com.revature.TeamCP2.beans.repositories.OrderRepository;
import com.revature.TeamCP2.beans.repositories.UserRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.Order;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.*;
import com.revature.TeamCP2.interfaces.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @MockBean OrderRepository orderRepositoryMock;
    @MockBean CartRepository cartRepositoryMock;
    @MockBean UserRepository userRepositoryMock;

    User testUser;
    Cart testCart;
    Order testOrder;

    @BeforeEach
    public void beforeEach() {
        testUser = new User(1, "username", "password", "holly", "last", "email@email.com", Role.USER, 1, null, null, null, null, null, null);
        testCart = new Cart(testUser, 200.00);
        testOrder = new Order(testCart, "2022-05-17");
    }

    @Test
    public void testCreateOrder(@Autowired OrderService orderService) throws ItemDoesNotExistException, CreationFailedException, ItemHasNonNullIdException {


        // mock repository methods used by OrderService createOrder with test objects
        when(userRepositoryMock.getById(testOrder.getCart().getUser().getId())).thenReturn(Optional.of(testUser));
        when(cartRepositoryMock.create(any())).thenReturn(testCart);
        when(orderRepositoryMock.create(testOrder)).thenReturn(testOrder);

        // call createOrder and compare result to our test objects
        Order returnedOrder = orderService.createOrder(testOrder);

        assertEquals(returnedOrder, testOrder);

        // verify cart and order create methods were called
        verify(cartRepositoryMock, times(1)).create(any());
        verify(orderRepositoryMock, times(1)).create(any());

    }

    @Test
    public void testUpdateOrder(@Autowired OrderService orderService) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {

        // mock repository methods used by OrderService updateOrder with test objects
        when(orderRepositoryMock.update(any())).thenReturn(testOrder);
        // fake old order with different cart and date
        Order oldOrder = new Order(new Cart(), "2022-05-13");
        // set old order id to be same as testOrder
        oldOrder.setId(testOrder.getId());

        // update old order to be same as test order and compare results
        Order returnedOrder = orderService.updateOrder(oldOrder);
        assertEquals(returnedOrder, testOrder);

        // verify  order repo update methods were called
        verify(orderRepositoryMock, times(1)).update(any());


    }

    @Test
    public void testGetOrderByUserId(@Autowired OrderService orderService) {
        // create test objects to interact with
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setId(1);
        user2.setId(2);
        user3.setId(3);

        Cart cart1 = new Cart(user1, 100.00);
        Cart cart2 = new Cart(user1, 200.00);
        Cart cart3 = new Cart(user2, 300.00);
        Cart cart4 = new Cart(user2, 400.00);

        Order order1 = new Order(cart1, "2022-05-01");
        Order order2 = new Order(cart2, "2022-05-02");
        Order order3 = new Order(cart3, "2022-05-03");
        Order order4 = new Order(cart4, "2022-05-04");

        List<Order> orders = new LinkedList<>(Arrays.asList(order1, order2, order3, order4));

        // mock repository methods used by OrderService getByUserId with test objects
        when(orderRepositoryMock.getAll()).thenReturn(orders);

        // call getByUser - orders 1 and 2 belong to user 1. orders 3 and 4 belong to user 2. No orders for user 3.
        List<Order> user1Orders = orderService.getByUserId(user1.getId());
        List<Order> user2Orders = orderService.getByUserId(user2.getId());
        List<Order> user3Orders = orderService.getByUserId(user3.getId());

        // compare result to our test objects
        assertTrue(user1Orders.contains(order1) && user1Orders.contains(order2));
        assertTrue(user2Orders.contains(order3) && user2Orders.contains(order4));
        assertTrue(user3Orders.isEmpty());

        // verify order repo methods were called
        verify(orderRepositoryMock, times(3)).getAll();

    }

    @Test
    public void testGetUserName(@Autowired OrderService orderService) throws ItemDoesNotExistException {

        testOrder.setId(1);
        String testUn = testOrder.getCart().getUser().getUsername();

        // mock repository methods used by OrderService getUsername with test objects
        when(orderRepositoryMock.getById(1)).thenReturn(Optional.of(testOrder));

        // call getUsername and compare results
        String returnedName = orderService.getUsername(1);
        assertEquals(returnedName, testUn);

        // verify order repo methods were called
        verify(orderRepositoryMock, times(2)).getById(1);

    }

    @Test
    public void testGetTotal(@Autowired OrderService orderService) throws ItemDoesNotExistException {

        testOrder.setId(1);
        Double total = testOrder.getCart().getTotal();

        // mock repository methods used by OrderService getUsername with test objects
        when(orderRepositoryMock.getById(1)).thenReturn(Optional.of(testOrder));

        // call getUsername and compare results
        Double returnedTotal = orderService.getTotal(1);

        assertEquals(returnedTotal, total);

        // verify order repo methods were called
        verify(orderRepositoryMock, times(2)).getById(1);

    }

}
