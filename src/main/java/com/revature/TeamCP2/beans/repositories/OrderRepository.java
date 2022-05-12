/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: Order Repository class used to implement CRUD functionality on Order
 * models that are persisted in the database
 */


package com.revature.TeamCP2.beans.repositories;


import com.revature.TeamCP2.entities.Order;
import com.revature.TeamCP2.exceptions.*;
import com.revature.TeamCP2.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepository extends AbstractHibernateRepo<Order> {

    private ConnectionManager conn;
    private Session session;
    private boolean run = false;
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
        Order updated = (Order) session.get(String.valueOf(Order.class), order.getId());
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

        // forcing me to use optional here - look into why again
        Optional<Order> toDelete = this.getById(id);
        session.delete(toDelete);
    }

    @Override
    public void delete(Order order) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {

        // convert long to int
        deleteById(order.getId().intValue());
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