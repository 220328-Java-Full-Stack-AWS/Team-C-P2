/**
 * Author(s): @Arun Mohan
 * Contributor(s):
 * Purpose: User Repository class used to implement CRUD functionality on User
 * models that are persisted in the database
 */

package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepository extends AbstractHibernateRepo<User> {

    private ConnectionManager conn;
    private Session session;
    private boolean run = false;

    // constructor - pass in connection
    @Autowired
    public UserRepository(ConnectionManager conn) {
        this.conn = conn;
    }


    // implement abstract class CRUD functionality

    @Override
    public User create(User user) throws ItemHasNonNullIdException, CreationFailedException {

        Transaction tran = session.beginTransaction();
        session.save(user);
        // commit transaction and return saved user
        tran.commit();
        return user;
    }

    @Override
    public User update(User user) throws ItemHasNoIdException, ItemDoesNotExistException, UpdateFailedException {

        Transaction tran = session.beginTransaction();
        // create a new user to update db using given user model
        User updated = session.get(User.class, user.getId());
        // populate fields using given user
        updated.setUsername(user.getUsername());
        updated.setPassword(user.getPassword());
        updated.setFirstName(user.getFirstName());
        updated.setLastName(user.getLastName());
        updated.setEmail(user.getEmail());
        updated.setUserAddress(user.getUserAddress());
        updated.setDateCreated(user.getDateCreated());
        updated.setDateModifies(user.getDateModifies());
        updated.setActiveCartId(user.getActiveCartId());
        updated.setPayment(user.getPayment());

        // save to session, commit, and return updated user
        session.save(updated);
        tran.commit();

        return updated;
    }

    @Override
    public void deleteById(int id) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {
        
        Optional<User> toDelete = this.getById(id);
        session.delete(toDelete);

    }


    @Override
    public void delete(User user) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {

        deleteById(user.getId());

    }

    // implement get by username method

    public Optional<User> getByUsername (String username) {
        List<User> users = this.getAll();
        // for each user, check if the username matches
        for (User u : users) {
            // if we find a match, assign the user
            if (u.getUsername().equals(username)) {
                 return Optional.of(u);
            }
        }
        return Optional.empty();
    }


    // override lifecycle methods

    @Override
    public void start() {
        this.session = conn.getSession();
        run = true;

    }

    @Override
    public void stop() {
        run = false;
    }

    @Override
    public boolean isRunning() {
        return run;
    }
}
