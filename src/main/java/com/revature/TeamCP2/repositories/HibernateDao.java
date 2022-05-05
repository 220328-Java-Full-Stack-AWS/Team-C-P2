package com.revature.TeamCP2.repositories;


import java.util.Collections;
import java.util.List;

import com.revature.TeamCP2.models.User;
import com.revature.TeamCP2.utils.BasicQuery;
import org.hibernate.Session;

public abstract class HibernateDao<T> {

    private Session session;

    public List<T> getAll(){
        //Does nothing right now
        return Collections.emptyList();
    }

    public T getById(int id){
        //Does Nothing right now
        return null;
    }

    public abstract void deleteById(int id);
    public abstract T updateById(int id);
}
