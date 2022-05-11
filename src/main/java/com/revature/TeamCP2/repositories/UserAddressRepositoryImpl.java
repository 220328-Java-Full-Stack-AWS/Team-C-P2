package com.revature.TeamCP2.repositories;

import com.revature.TeamCP2.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserAddressRepositoryImpl implements UserAddressRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User save(User user){
        entityManager.persist(user);
        return user;
    }
}