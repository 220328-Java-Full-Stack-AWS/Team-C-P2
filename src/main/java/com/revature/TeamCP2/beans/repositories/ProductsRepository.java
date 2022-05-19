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


    public Product create(Product product) {
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        return product;
    }


    @Override
    public Optional<Product> getById(int id) {

        Product product = session.get(Product.class, id);

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
            product.setOnSale(result.getOnSale());

            productList.add(product);
        }
        return productList;
    }

    public List<Product> getAllFeatured() {
        Query query = session.createQuery("from Products where is_featured = true");

        List<Product> results = query.list();

        List<Product> featuredList = new LinkedList<>();

        for (Product result : results) {
            if (result.isIs_featured()) {
                Product product = new Product();
                product.setId(result.getId());
                product.setDescr(result.getDescr());
                product.setImage(result.getImage());
                product.setIs_featured(result.isIs_featured());
                product.setName(result.getName());
                product.setPrice(result.getPrice());
                product.setCategory(result.getCategory());
                product.setOnSale(result.getOnSale());

                featuredList.add(product);
            }
        }


        return featuredList;
    }

    @Override
    public void deleteById(int id) {
        Transaction transaction = session.beginTransaction();
        Product product = (Product) session.get(Product.class, id);
        session.delete(product);
        transaction.commit();
    }

    @Override
    public Product update(Product product) {
        Transaction transaction = session.beginTransaction();

        Product updateProduct = session.get(Product.class, product.getId());
        updateProduct.setDescr(product.getDescr());
        updateProduct.setImage(product.getImage());
        updateProduct.setIs_featured(product.isIs_featured());
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setCategory(product.getCategory());
        updateProduct.setOnSale(product.getOnSale());

        session.saveOrUpdate(updateProduct);

        transaction.commit();

        return updateProduct;
    }

    @Override
    public void delete(Product model) throws ItemHasNoIdException {
        if (model.getId() == null)
            throw new ItemHasNoIdException();

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
