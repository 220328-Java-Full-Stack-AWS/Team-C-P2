/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Uses JPA CriteriaBuilder to make getAll & getById
 *      requests on any class that includes hibernate annotations and is explicitly mentioned
 *      when starting the connection in main.
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Scope("prototype")
public class BasicQuery<T> {

    private Session session;
    private Class<T> aClass;

    public BasicQuery() {}

    /**
     * Constructor
     * @param aClass The type you want the query to act on
     * @GH
     */
    public BasicQuery(Class<T> aClass){
        this.aClass = aClass;
    }

    /**
     * Constructor
     * @param s Specific session you would like to use (otherwise uses ConnectionManager session)
     * @param aClass The type you want the query to act on
     * @GH
     */
    public BasicQuery(Session s, Class<T> aClass){
        this.session = s;
        this.aClass = aClass;
    }

    /**
     * Gets all the entities associated with the object type
     * @GH
     */
    public List<T> getAll(){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(aClass);

        query = query.select(query.from(aClass));

        return session.createQuery(query).getResultList();
    }

    /**
     * Gets the entity associated with the id passed in
     * @GH
     */
    public T getById(int id) throws ItemDoesNotExistException {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(aClass);
        Root<T> root = query.from(aClass);

        try{
            query = query.select(root).where(cb.equal(root.get("id"), id));
            return session.createQuery(query).getSingleResult();
        } catch(NoResultException noResultException){
            throw new ItemDoesNotExistException("No item with the id: " + id);
        }
    }

    @Autowired
    public void setSession(ConnectionManager connectionManager) {
        this.session = connectionManager.getSession();
    }

    public Class<T> getaClass() {
        return aClass;
    }

    public void setaClass(Class<T> aClass) {
        this.aClass = aClass;
    }
}
