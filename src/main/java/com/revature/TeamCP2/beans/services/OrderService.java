/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Service class used to implement OrderRepository methods and define
 * additional functionality.
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.OrderRepository;
import com.revature.TeamCP2.entities.Order;
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

    public Order createOrder(Order order) {
        return new Order();
    }

    public Order updateOrder(Order order) {
        return new Order();
    }

    public void deleteOrder(Order order) {

    }

    public Optional<Order> getOrderById(Integer id) {
        return Optional.of(new Order());
    }

    public List<Order> getAll() {
        return new LinkedList<Order>();
    }

    public List<Order> getByUserId(Integer userId) {
        return new LinkedList<Order>();
    }

    public String getUsername(Integer id) {
        return "";
    }

    public Double getTotal(Integer id) {
        return 0.0;
    }

}
