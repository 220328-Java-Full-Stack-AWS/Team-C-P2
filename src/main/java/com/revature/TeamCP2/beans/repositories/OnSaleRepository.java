/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: OnSaleDao
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.OnSale;

import com.revature.TeamCP2.entities.Order;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class OnSaleRepository extends AbstractHibernateRepo<OnSale> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public OnSaleRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public OnSale create(OnSale onSale) {
        Transaction transaction = session.beginTransaction();
        session.save(onSale);
        transaction.commit();
        return onSale;
    }


    @Override
    public void deleteById(int id) throws ItemDoesNotExistException {

        Transaction transaction = session.beginTransaction();
        Optional<OnSale> onSale = this.getById(id);
        session.delete(onSale.get());
        transaction.commit();
    }


    @Override
    public OnSale update(OnSale onSale) {
        Transaction transaction = session.beginTransaction();
        OnSale updateOnSale = session.get(OnSale.class, onSale.getId());

        updateOnSale.setId(onSale.getId());
        updateOnSale.setDiscount(onSale.getDiscount());

        session.saveOrUpdate(updateOnSale);

        transaction.commit();

        return updateOnSale;
    }

    @Override
    public void delete(OnSale model) throws ItemDoesNotExistException {
        deleteById(model.getId());
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
