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
    public User updateById(int id) {
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

/*
public User update(User user) {
        User updateUser = this.getById(user.getId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPassword(user.getPassword());
        this.save(updateUser);
        return user;
        //TODO: Find a better way to do this
    }

public List<User> getAll() {
        String sql = "SELECT * FROM users";
        Query query = session.createNativeQuery(sql);
        //query.setParameter("table", tableName);
        List<Object[]> results = query.getResultList();

        List<User> userList = new LinkedList<>();
        for(Object[] result : results) {
            User user = new User();
            user.setId((Integer)result[0]);
            user.setFirstName((String)result[1]);
            user.setLastName((String)result[2]);
            user.setPassword((String)result[3]);
            user.setUsername((String)result[4]);
            userList.add(user);
        }
        return userList;

public User getById(Integer id) {
        String hql = "FROM User WHERE id = :id";
        TypedQuery<User> query = session.createQuery(hql, User.class);

        query.setParameter("id", id);

        User user = query.getSingleResult();

        return user;
    }


 */
