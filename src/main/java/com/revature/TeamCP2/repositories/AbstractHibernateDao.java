package com.revature.TeamCP2.repositories;


import java.util.List;
import java.util.Optional;


import com.revature.TeamCP2.utils.BasicQuery;
import org.springframework.core.GenericTypeResolver;

public abstract class AbstractHibernateDao<T> {

    public List<T> getAll(){
        Class<T> type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHibernateDao.class);
        BasicQuery<T> query = new BasicQuery<>(type);

        return query.getAll();
    }

    public Optional<T> getById(int id){
        Class<T> type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHibernateDao.class);
        BasicQuery<T> query = new BasicQuery<>(type);

        return query.getById(id);
    }

    public abstract void deleteById(int id);
    public abstract T updateById(int id);
}
