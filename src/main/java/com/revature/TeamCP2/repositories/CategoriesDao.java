package com.revature.TeamCP2.repositories;

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

public class CategoriesDao extends AbstractHibernateDao<ProductCategory>{
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public CategoriesDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    //@Override
    public ProductCategory create(ProductCategory productCategory) {
        Transaction transaction =session.beginTransaction();
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
        for(ProductCategory result : results) {

            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(result.getId());
            productCategory.setDescription(result.getDescription());
            productCategory.setImage(result.getImage());
            productCategory.setName(result.getName());
//            productCategory.setProductsAssociated(results)


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
    public ProductCategory updateById(int id) {
        Optional<ProductCategory> updateProduct = this.getById(id);
        session.saveOrUpdate(updateProduct);


        return null;
    }
}
