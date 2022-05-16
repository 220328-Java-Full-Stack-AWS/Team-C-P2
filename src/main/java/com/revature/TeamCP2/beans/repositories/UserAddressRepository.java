package com.revature.TeamCP2.beans.repositories;

import com.revature.TeamCP2.beans.services.ConnectionManager;

import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.entities.UserAddress;
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
public class UserAddressRepository extends AbstractHibernateRepo<UserAddress> {
    private ConnectionManager connectionManager;
    private Session session;
    private boolean running = false;

    @Autowired
    public UserAddressRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    //@Override
    public UserAddress create(UserAddress userAddress) {
        Transaction transaction = session.beginTransaction();
        session.save(userAddress);
        transaction.commit();
        return userAddress;
    }


    @Override
    public Optional<UserAddress> getById(int id) {


        TypedQuery<UserAddress> query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);

        UserAddress userAddress = query.getSingleResult();

        return Optional.ofNullable(userAddress);
    }

    @Override
    public List<UserAddress> getAll() {
        Query query = session.createQuery("from UserAddress  ");

        List<UserAddress> results = query.list();

        List<UserAddress> userAddressList = new LinkedList<>();

        for (UserAddress result : results) {
            UserAddress userAddress = new UserAddress();
            userAddress.setId(result.getId());
            userAddress.setAddressLine1(result.getAddressLine1());
            userAddress.setAddressLine2(result.getAddressLine2());
            userAddress.setCity(result.getCity());
            userAddress.setPhoneNumber(result.getPhoneNumber());
            userAddress.setPostalCode(result.getPostalCode());
            userAddress.setCountry(result.getCountry());

            userAddressList.add(userAddress);
        }
        return userAddressList;
    }

    @Override
    public void deleteById(int id) {
        Optional<UserAddress> userAddress = this.getById(id);
        session.delete(userAddress);
    }

    @Override
    public UserAddress update(UserAddress userAddress) {
        Transaction transaction = session.beginTransaction();


        UserAddress updateUserAddress = session.get(UserAddress.class, userAddress.getId());
        updateUserAddress.setAddressLine2(userAddress.getAddressLine2());
        updateUserAddress.setAddressLine1(userAddress.getAddressLine1());
        updateUserAddress.setCity(userAddress.getCity());
        updateUserAddress.setPostalCode(userAddress.getPostalCode());
        updateUserAddress.setCountry(userAddress.getCountry());
        updateUserAddress.setPhoneNumber(userAddress.getPhoneNumber());

        session.save(updateUserAddress);

        transaction.commit();

        return updateUserAddress;
    }

    @Override
    public void delete(UserAddress model) throws ItemHasNoIdException {
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


