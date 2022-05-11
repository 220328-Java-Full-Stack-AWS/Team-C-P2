/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: OnSaleDao
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.OnSale;

import com.revature.TeamCP2.repositories.AbstractHibernateRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
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
//        String hql = "FROM on_sale WHERE id = :id";
//        TypedQuery<OnSale> query = session.createQuery(hql, OnSale.class);
        TypedQuery<OnSale> query = session.createQuery("from ProductCategory where id = :id");

        query.setParameter("id", id);

        OnSale OnSale = query.getSingleResult();

        return Optional.ofNullable(OnSale);
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
        Optional<OnSale> sale = this.getById(id);
        session.delete(sale);
    }


    @Override
    public OnSale update(OnSale onSale) {
        Transaction transaction = session.beginTransaction();
        Optional<OnSale> updateOnSale = (Optional<OnSale>)
                session.get(String.valueOf(OnSale.class), onSale.getId());

        updateOnSale.get().setDiscount(onSale.getDiscount());
        updateOnSale.get().setProductsOnSale(onSale.getProductsOnSale());

        transaction.commit();
        return null;
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
