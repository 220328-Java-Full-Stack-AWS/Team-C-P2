/**
 Author: Steven Dowd
 Purpose: Repo for cart objects
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.repositories.AbstractHibernateRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class CartRepository extends AbstractHibernateRepo<Cart> {

    private final ConnectionManager connectionManager;
    private boolean running = false;
    private Session session;
    private String tableName;

    @Autowired
    public CartRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Cart create(Cart cart) {
        Transaction tx = session.beginTransaction();
        session.save(cart);
        tx.commit();

        return cart;
    }


    @Override
    public void deleteById(int id) {
        Optional<Cart> cart = this.getById(id);
        session.delete(cart);
    }

    public void delete(Cart cart) {
        session.delete(cart);
    }

    public Optional<Cart> getById(int id) {
        TypedQuery<Cart> query = session.createQuery("from Cart where id = :id");
        query.setParameter("id", id);

        Cart cart = query.getSingleResult();

        return Optional.ofNullable(cart);
    }

    @Override
    public Cart update(Cart cart) {
        Transaction transaction = session.beginTransaction();
        Optional<Cart> updateCart = (Optional<Cart>)
                session.get(String.valueOf(Cart.class), cart.getId());

        updateCart.get().setUserId(cart.getUserId());
        updateCart.get().setTotal(cart.getTotal());

        transaction.commit();

        return cart;
    }

    public List<Cart> getAll() {
        Query query = session.createQuery("from Cart ");

        List<Cart> results = query.getResultList();

        List<Cart> cartList = new LinkedList<>();

        for (Cart result: results) {
            Cart cart = new Cart();
            cart.setId(result.getId());
            cart.setUserId(result.getUserId());
            cart.setTotal(result.getTotal());

            cartList.add(cart);
        }
        return cartList;
    }

    @Override
    public void start() {
        this.session = connectionManager.getSession();
        running = true;

    }

    @Override
    public void stop() {
        running = false;

    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
