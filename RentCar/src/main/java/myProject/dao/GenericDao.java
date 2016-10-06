package myProject.dao;

import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface GenericDao<E, ID> {
    void save(E entity);

    void update(E entity);

    void delete(E entity);

    E findOne(ID id);

    List<E> findAll();
}
