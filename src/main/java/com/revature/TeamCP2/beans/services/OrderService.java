/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Service class used to implement OrderRepository methods and define
 * additional functionality.
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.OrderRepository;
import com.revature.TeamCP2.entities.Order;
import com.revature.TeamCP2.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    // constructor

    @Autowired
    public OrderService (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) throws CreationFailedException, ItemHasNonNullIdException {
        return orderRepository.create(order);
    }

    public Order updateOrder(Order order) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        return orderRepository.update(order);
    }

    public void deleteOrder(Order order) throws ItemDoesNotExistException, DeletionFailedException, ItemHasNoIdException {
        orderRepository.delete(order);
    }

    public Optional<Order> getOrderById(Integer id) throws ItemDoesNotExistException {
        return orderRepository.getById(id);
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    /**
     * Get a list of all orders placed by the user with the given id
     * @param userId User Id
     * @return list of Orders containing Carts which have given user id
     */
    public List<Order> getByUserId(Integer userId) {
        List<Order> orders = orderRepository.getAll();
        List<Order> userOrders = new LinkedList<>();
        // add all orders with given user id to list and return
        for (Order o : orders) {
            if (o.getCart().getUser().getId() == userId) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    /**
     * Get the username associated with the user who placed the given order
     * @param id Order id
     * @return username associated with given order
     * @throws ItemDoesNotExistException if given order does not exist
     */
    public String getUsername(Integer id) throws ItemDoesNotExistException {
        if (orderRepository.getById(id).isPresent()) {
            return orderRepository.getById(id).get().getCart().getUser().getUsername();
        }
        return " ";
    }

    /**
     * Get the total dollar value of the cart associated with the given order
     * @param id Order id
     * @return double total value of the cart
     * @throws ItemDoesNotExistException if given order does not exist
     */
    public Double getTotal(Integer id) throws ItemDoesNotExistException {
        if (orderRepository.getById(id).isPresent()) {
            return orderRepository.getById(id).get().getCart().getTotal();
        }
        return 0.0;
    }

}
