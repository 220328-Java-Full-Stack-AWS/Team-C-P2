/**
 * Author: Steven Dowd
 * Purpose: Repo for cart item objects
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    public CartItem create(CartItem cartItem) {
        Transaction tx = session.beginTransaction();
        session.save(cartItem);
        tx.commit();

        return cartItem;
    }

    @Override
    public void deleteById(int id) throws ItemDoesNotExistException {
        Transaction tx = session.beginTransaction();
        if (this.getById(id).isPresent()) {
            CartItem cartItem = this.getById(id).get();
            session.delete(cartItem);
        }
        tx.commit();
    }

    public void delete(CartItem cartItem) throws ItemDoesNotExistException {
        Transaction tx = session.beginTransaction();
        session.delete(cartItem);
        tx.commit();

    }

    @Override
    public CartItem update(CartItem cartItem) {
        Transaction transaction = session.beginTransaction();
        CartItem updateCartItem = session.get(CartItem.class, cartItem.getId());

        updateCartItem.setCart(cartItem.getCart());
        updateCartItem.setProduct(cartItem.getProduct());
        updateCartItem.setQuantity(cartItem.getQuantity());
        updateCartItem.setNetPrice(cartItem.getNetPrice());

        session.save(updateCartItem);
        transaction.commit();

        return updateCartItem;
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
            cartItem.setNetPrice(result.getNetPrice());

            cartItemList.add(cartItem);
        }
        return cartItemList;
    }

    public List<CartItem> getAllCartItemsByCart(Cart cart) {
        Query query = session.createQuery("from CartItem where cart = :cart");

        query.setParameter("cart", cart);

        List<CartItem> resultList = query.getResultList();
        List<CartItem> list = new ArrayList<>();
        for (CartItem cartItem : resultList) {
            CartItem item = new CartItem();
            item.setCart(cartItem.getCart());
            item.setId(cartItem.getId());
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setNetPrice(cartItem.getNetPrice());

            list.add(item);
        }
        return list;
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
