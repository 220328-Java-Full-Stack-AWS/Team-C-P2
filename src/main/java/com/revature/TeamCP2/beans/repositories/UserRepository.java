/**
 * UserRepository is a test file to see if objects would be persisted by @Autowiring the ConnectionManager.
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.repositories.AbstractHibernateRepo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRepository extends AbstractHibernateRepo {
    private final ConnectionManager connectionManager;
    private boolean running = false;
    private Session session;
    private String tableName;

    @Autowired
    public UserRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public User create(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();

        return user;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Object update(Object o) {
        return null;
    }


    public void start() {
        this.session = connectionManager.getSession();
        running = true;
    }


    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    @Value("users")
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}

