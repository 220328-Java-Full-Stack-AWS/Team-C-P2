/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: ConnectionManager provides implementations to persist or retrieve user objects.
 *          UserService @Autowires UserRepository.
 *
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConnectionManager implements Lifecycle {
    private boolean running = false;

    private final List<Class> models;
    private Configuration config;
    private SessionFactory sessionFactory;
    private Session session;

    public ConnectionManager() {
        config = new Configuration();
        sessionFactory = config.buildSessionFactory();
        models = new LinkedList<>();
    }

    @Override
    public void start() {

        models.add(User.class);
        models.add(Cart.class);
        models.add(CartItem.class);
        models.add(OnSale.class);
        models.add(Order.class);
        models.add(Payment.class);
        models.add(Product.class);
        models.add(ProductCategory.class);
        models.add(UserAddress.class);

        for (Class model : models) {
            config.addAnnotatedClass(model);
        }

        sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
        session.close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void addModel(Class model) {
        models.add(model);
    }

    public List<Class> getModels() {
        return models;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
