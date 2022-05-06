/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Uses JPA CriteriaBuilder to make getAll & getById
 *      requests on any class that includes hibernate annotations and is explicitly mentioned
 *      when starting the connection in main.
 */

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

    /**
     * Constructor
     * @param aClass The type you want the query to act on
     */
    public BasicQuery(Class<T> aClass){
        this.session = ConnectionManager.getConnection().getSession();
        this.aClass = aClass;
    }

    /**
     * Constructor
     * @param s Specific session you would like to use (otherwise uses ConnectionManager session)
     * @param aClass The type you want the query to act on
     */
    public BasicQuery(Session s, Class<T> aClass){
        this.session = s;
        this.aClass = aClass;
    }

    /**
     * Gets all the entities associated with the object type
     */
    public List<T> getAll(){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(aClass);

        query = query.select(query.from(aClass));

        return session.createQuery(query).getResultList();
    }

    /**
     * Gets the entity associated with the id passed in
     */
    public Optional<T> getById(int id){

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(aClass);
        Root<T> root = query.from(aClass);

        T result = null;
        try{
            query = query.select(root).where(cb.equal(root.get("id"), id));
            result = session.createQuery(query).getSingleResult();
        } catch(NoResultException ignored){}
        return Optional.ofNullable(result);
    }
}
