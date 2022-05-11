package com.revature.TeamCP2.repositories;

import com.revature.TeamCP2.models.OnSale;
import com.revature.TeamCP2.models.ProductCategory;
import com.revature.TeamCP2.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OnSaleDao extends AbstractHibernateDao<OnSale>{
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public OnSaleDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    //@Override
    public OnSale create(OnSale onSale) {
        Transaction transaction = session.beginTransaction();
        session.save(onSale);
        transaction.commit();
        return onSale;
    }

    //@Override
    public ProductCategory get(OnSale obj) {
        return null;
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

        for (OnSale result:results) {
            OnSale onSale =  new OnSale();
            onSale.setId(result.getId());
            onSale.setDiscount(result.getDiscount());

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
    public OnSale updateById(int id) {

        return null;
    }
}
