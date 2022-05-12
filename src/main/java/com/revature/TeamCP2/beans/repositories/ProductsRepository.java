/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: ProductsDao
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;

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
public class ProductsRepository extends AbstractHibernateRepo<Product> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public ProductsRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    //@Override
    public Product create(Product product) {
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        return product;
    }


    @Override
    public Optional<Product> getById(int id) {
//        String hql = "FROM products WHERE id = :id";
//        TypedQuery<Product> query = session.createQuery(hql, Product.class);

        TypedQuery<Product> query = session.createQuery("from ProductCategory where id = :id");
        query.setParameter("id", id);

        Product product = query.getSingleResult();

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAll() {
        Query query = session.createQuery("from Products ");

        List<Product> results = query.list();

        List<Product> productList = new LinkedList<>();

        for (Product result : results) {
            Product product = new Product();
            product.setId(result.getId());
            product.setDescr(result.getDescr());
            product.setImage(result.getImage());
            product.setIs_featured(result.isIs_featured());
            product.setName(result.getName());
            product.setPrice(result.getPrice());
            product.setCategory(result.getCategory());

            productList.add(product);
        }
        return productList;
    }

    @Override
    public void deleteById(int id) {
        Optional<Product> product = this.getById(id);
        session.delete(product);
    }

    @Override
    public Product update(Product product) {
        Transaction transaction = session.beginTransaction();
        Optional<Product> updateProduct = (Optional<Product>)
                session.get(String.valueOf(Product.class), product.getId());
//        Optional<Product> product1 = this.getById(product.getId());
        updateProduct.get().setDescr(product.getDescr());
        updateProduct.get().setImage(product.getImage());
        updateProduct.get().setIs_featured(product.isIs_featured());
        updateProduct.get().setName(product.getName());
        updateProduct.get().setPrice(product.getPrice());
        updateProduct.get().setCategory(product.getCategory());

        transaction.commit();


        return null;
    }

    @Override
    public void delete(Product model) throws ItemHasNoIdException {
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
