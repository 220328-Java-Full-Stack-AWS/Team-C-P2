/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Implements the base functionality and required methods of
 *      future entity DAOs.
 */
package com.revature.TeamCP2.beans.repositories;

import java.util.List;
import com.revature.TeamCP2.exceptions.*;
import com.revature.TeamCP2.beans.services.BasicQuery;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.Lifecycle;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractHibernateRepo<T> implements Lifecycle, ApplicationContextAware {

    ApplicationContext context;

    /**
     * Uses BasicQuery
     * @GH
     */
    public List<T> getAll(){
        Class<T> type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHibernateRepo.class);
        BasicQuery<T> query = context.getBean(BasicQuery.class, type);

        return query.getAll();
    }

    /**
     * Uses BasicQuery
     * @GH
     */
    public T getById(int id) throws ItemDoesNotExistException {
        Class<T> type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHibernateRepo.class);
        BasicQuery<T> query = context.getBean(BasicQuery.class, type);

        return query.getById(id);
    }

    public abstract T create(T model) throws ItemHasNonNullIdException, CreationFailedException;
    public abstract T update(T model) throws ItemHasNoIdException, ItemDoesNotExistException, UpdateFailedException;
    public abstract void delete(T model) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException;
    public abstract void deleteById(int id) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
