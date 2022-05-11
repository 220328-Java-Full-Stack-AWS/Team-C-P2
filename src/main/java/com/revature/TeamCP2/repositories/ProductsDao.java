package com.revature.TeamCP2.repositories;

import com.revature.TeamCP2.models.OnSale;
import com.revature.TeamCP2.models.Product;
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

public class ProductsDao extends AbstractHibernateDao<Product> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public ProductsDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    //@Override
    public Product create(Product product) {
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        return product;
    }

    //@Override
    public ProductCategory get(Product obj) {
        return null;
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

        for (Product result:results) {
            Product product =  new Product();
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
    public Product updateById(int id) {

        return null;
    }
}
