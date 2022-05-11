/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Implements the base functionality and required methods of
 * future entity DAOs.
 */
package com.revature.TeamCP2.repositories;

import java.util.List;
import java.util.Optional;

import com.revature.TeamCP2.models.Product;
import com.revature.TeamCP2.utils.BasicQuery;
import org.springframework.context.Lifecycle;
import org.springframework.core.GenericTypeResolver;

public abstract class AbstractHibernateRepo<T> implements Lifecycle {

    /**
     * Uses BasicQuery
     *
     * @GH
     */

    public List<T> getAll() {
        Class<T> type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHibernateDao.class);


        return query.getAll();
    }

    /**
     * Uses BasicQuery
     *
     * @GH
     */

    public Optional<T> getById(int id) {
        Class<T> type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHibernateRepo.class);
        BasicQuery<T> query = new BasicQuery<>(type);

        return query.getById(id);
    }

    public abstract void deleteById(int id);

    public abstract T updateById(int id);


}
