package com.revature.TeamCP2.repositories;

import com.revature.TeamCP2.models.User;

public class UserDao extends HibernateDao<User>{
    @Override
    public void deleteById(int id) {

    }

    @Override
    public User updateById(int id) {
        return null;
    }
}
