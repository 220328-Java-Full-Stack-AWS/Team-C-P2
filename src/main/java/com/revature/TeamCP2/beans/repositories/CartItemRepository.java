/**
 * Author: Steven Dowd
 * Purpose: Repo for cart item objects
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.CartItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class CartItemRepository extends AbstractHibernateRepo<CartItem> {

    private final ConnectionManager connectionManager;
    private boolean running = false;
    private Session session;
    private String tableName;

    @Autowired
    public CartItemRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /*
  create(model) x
  update(model) x
  delete(model) x
  getByID(int id) x
  getAll() x
     */
    public CartItem create(CartItem cartItem) {
        Transaction tx = session.beginTransaction();
        session.save(cartItem);
        tx.commit();

        return cartItem;
    }

    @Override
    public void deleteById(int id) {
        Optional<CartItem> cartItem = this.getById(id);
        session.delete(cartItem);
    }

    public void delete(CartItem cartItem) {
        session.delete(cartItem);
    }

    public Optional<CartItem> getById(int id) {
        TypedQuery<CartItem> query = session.createQuery("from CartItem where id = :id");
        query.setParameter("id", id);

        CartItem cartItem = query.getSingleResult();

        return Optional.ofNullable(cartItem);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        Transaction transaction = session.beginTransaction();
        Optional<CartItem> updateCartItem = (Optional<CartItem>)
                session.get(String.valueOf(CartItem.class), cartItem.getId());

        updateCartItem.get().setCart(cartItem.getCart());
        updateCartItem.get().setProduct(cartItem.getProduct());
        updateCartItem.get().setQuantity(cartItem.getQuantity());

        transaction.commit();

        return cartItem;
    }

    public List<CartItem> getAll() {
        Query query = session.createQuery("from CartItem");

        List<CartItem> results = query.getResultList();

        List<CartItem> cartItemList = new LinkedList<>();

        for (CartItem result : results) {
            CartItem cartItem = new CartItem();
            cartItem.setId(result.getId());
            cartItem.setCart(result.getCart());
            cartItem.setProduct(result.getProduct());
            cartItem.setQuantity(result.getQuantity());

            cartItemList.add(cartItem);
        }
        return cartItemList;
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

    @Value("cart_items")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
