package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.repositories.AbstractHibernateRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CartItemRepository extends AbstractHibernateRepo {

    private final ConnectionManager connectionManager;
    private boolean running = false;
    private Session session;
    private String tableName;

    @Autowired
    public CartItemRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /*
  create(model)
  update(model)
  delete(model)
  getByID(int id)
  getAll()
     */
    public CartItem create (CartItem cartItem) {
        Transaction tx = session.beginTransaction();
        session.save(cartItem);
        tx.commit();

        return cartItem;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public CartItem updateById(int id) {
        CartItem cartItem = CartItem.getById(id);

        return cartItem;
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
