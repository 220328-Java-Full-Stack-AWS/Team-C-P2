package com.revature.TeamCP2.utils;

import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class BasicQuery<T> {

    private final Session session;
    private final Class<T> aClass;

    public BasicQuery(Session s, Class<T> aClass){
        this.session = s;
        this.aClass = aClass;
    }

    public List<T> getAll(){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(aClass);

        query = query.select(query.from(aClass));

        return session.createQuery(query).getResultList();
    }

    public Optional<T> getById(int id){

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(aClass);
        Root<T> root = query.from(aClass);

        T result = null;
        try{
            query = query.select(root).where(cb.equal(root.get("id"), id));
            result = session.createQuery(query).getSingleResult();
            System.out.println(result);
        } catch(NoResultException ignored){}
        return Optional.ofNullable(result);
    }
}
