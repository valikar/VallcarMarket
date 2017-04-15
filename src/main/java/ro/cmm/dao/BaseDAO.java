package ro.cmm.dao;

import ro.cmm.domain.AbstractModel;

import java.util.Collection;

/**
 * @author Emanuel Pruker
 */
public interface BaseDAO<T extends AbstractModel> {

    Collection<T> getAll();

    T findById(Long id);

    T update(T model);

    boolean delete(T model);
}