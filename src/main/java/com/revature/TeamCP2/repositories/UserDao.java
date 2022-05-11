package com.revature.TeamCP2.repositories;

import com.revature.TeamCP2.entities.User;

public class UserDao extends AbstractHibernateRepo<User> {
    @Override
    public void deleteById(int id) {

    }

    @Override
    public User updateById(int id) {
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
