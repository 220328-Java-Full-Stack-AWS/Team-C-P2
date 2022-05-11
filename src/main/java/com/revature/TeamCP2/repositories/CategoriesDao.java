/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: CategoryDao
 */

package com.revature.TeamCP2.repositories;


import com.revature.TeamCP2.entities.ProductCategory;
import com.revature.TeamCP2.utils.ConnectionManager;
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
public class CategoriesDao extends AbstractHibernateRepo<ProductCategory> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public CategoriesDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    //@Override
    public ProductCategory create(ProductCategory productCategory) {
        Transaction transaction = session.beginTransaction();
        session.save(productCategory);
        transaction.commit();
        return productCategory;
    }

    //@Override
    public ProductCategory get(ProductCategory productCategory) {
        return null;
    }


    @Override
    public Optional<ProductCategory> getById(int id) {
//        String hql = "from ProductCategory where id = :id";
//        TypedQuery<ProductCategory> query = session.createQuery(hql, ProductCategory.class);

        TypedQuery<ProductCategory> query = session.createQuery("from ProductCategory where id = :id");

        query.setParameter("id", id);

        ProductCategory productCategory = query.getSingleResult();

        return Optional.ofNullable(productCategory);
    }

    @Override
    public List<ProductCategory> getAll() {

//        String sql = "SELECT * FROM product_categories";
//        Query query = session.createNativeQuery(sql);

        Query query = session.createQuery("from ProductCategory ");

        List<ProductCategory> results = query.list();

        List<ProductCategory> productCategoryList = new LinkedList<>();
        for (ProductCategory result : results) {

            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(result.getId());
            productCategory.setDescription(result.getDescription());
            productCategory.setImage(result.getImage());
            productCategory.setName(result.getName());
            productCategory.setProductsAssociated(result.getProductsAssociated());


            productCategoryList.add(productCategory);
        }
        return productCategoryList;
    }

    @Override
    public void deleteById(int id) {
        Optional<ProductCategory> productCategory = this.getById(id);
        session.delete(productCategory);
    }
    
    @Override
    public ProductCategory update(ProductCategory productCategory) {

        Transaction transaction = session.beginTransaction();
        Optional<ProductCategory> updateProductCategory = (Optional<ProductCategory>)
                session.get(String.valueOf(ProductCategory.class), productCategory.getId());

        updateProductCategory.get().setDescription(productCategory.getDescription());
        updateProductCategory.get().setImage(productCategory.getImage());
        updateProductCategory.get().setName(productCategory.getName());
        updateProductCategory.get().setProductsAssociated(productCategory.getProductsAssociated());

        transaction.commit();

        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
