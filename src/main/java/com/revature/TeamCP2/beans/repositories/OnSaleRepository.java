/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: OnSaleDao
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.OnSale;

import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class OnSaleRepository extends AbstractHibernateRepo<OnSale> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public OnSaleRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    //@Override
    public OnSale create(OnSale onSale) {
        Transaction transaction = session.beginTransaction();
        session.save(onSale);
        transaction.commit();
        return onSale;
    }


    @Override
    public Optional<OnSale> getById(int id) {

        OnSale onSale = session.get(OnSale.class, id);

        return Optional.ofNullable(onSale);
    }


    @Override
    public List<OnSale> getAll() {
        Query query = session.createQuery("from OnSale");

        List<OnSale> results = query.list();

        List<OnSale> onSaleList = new LinkedList<>();

        for (OnSale result : results) {
            OnSale onSale = new OnSale();
            onSale.setId(result.getId());
            onSale.setDiscount(result.getDiscount());
            onSale.setProductsOnSale(result.getProductsOnSale());

            onSaleList.add(onSale);
        }

        return onSaleList;
    }

    @Override
    public void deleteById(int id) {
        Transaction transaction = session.beginTransaction();
        OnSale onSale = (OnSale) session.get(OnSale.class, id);
        session.delete(onSale);
        transaction.commit();
    }


    @Override
    public OnSale update(OnSale onSale) {
        Transaction transaction = session.beginTransaction();
        OnSale updateOnSale = session.get(OnSale.class, onSale.getId());

        updateOnSale.setDiscount(onSale.getDiscount());
        updateOnSale.setProductsOnSale(onSale.getProductsOnSale());

        session.saveOrUpdate(updateOnSale);

        transaction.commit();

        return updateOnSale;
    }

    @Override
    public void delete(OnSale model) throws ItemHasNoIdException {
        if (model.getId() == null)
            throw new ItemHasNoIdException();

        deleteById(model.getId().intValue());
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
