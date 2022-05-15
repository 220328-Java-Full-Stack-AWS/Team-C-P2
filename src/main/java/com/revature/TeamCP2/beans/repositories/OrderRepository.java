/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Order Repository class used to implement CRUD functionality on Order
 * models that are persisted in the database
 */


package com.revature.TeamCP2.beans.repositories;


import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.Order;
import com.revature.TeamCP2.exceptions.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepository extends AbstractHibernateRepo<Order> {

    private ConnectionManager conn;
    private Session session;
    private boolean run = false;

    // constructor

    @Autowired
    public OrderRepository(ConnectionManager conn) {
        this.conn = conn;
    }

    // implement abstract class CRUD functions

    @Override
    public Order create(Order order) throws ItemHasNonNullIdException, CreationFailedException {
        Transaction tran = session.beginTransaction();
        session.save(order);
        // commit transaction and return saved order
        tran.commit();

        return order;
    }

    @Override
    public Order update(Order order) throws ItemHasNoIdException, ItemDoesNotExistException, UpdateFailedException {
        Transaction tran = session.beginTransaction();
        // create a new order to update db using given order model
        Order updated = session.get(Order.class, order.getId());
        // populate fields using given user
        updated.setCart(order.getCart());
        updated.setDateCreated(order.getDateCreated());

        // save to session, commit, and return updated order
        session.save(updated);
        tran.commit();

        return updated;
    }


    @Override
    public void deleteById(int id) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {

        Optional<Order> toDelete = this.getById(id);
        session.delete(toDelete);
    }

    @Override
    public void delete(Order order) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {

        deleteById(order.getId());
    }

    // override lifecycle methods

    @Override
    public void start() {
        this.session = conn.getSession();
        run = true;

    }

    @Override
    public void stop() {
        run = false;
    }

    @Override
    public boolean isRunning() {
        return run;
    }
}