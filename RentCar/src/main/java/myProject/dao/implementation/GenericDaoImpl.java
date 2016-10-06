package myProject.dao.implementation;

import myProject.dao.GenericDao;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public abstract class GenericDaoImpl<E, ID> implements GenericDao<E, ID> {
    private final Class<E> entityClass;
    protected final EntityManager em;

    protected GenericDaoImpl(Class<E> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    public void save(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void update(E entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void delete(E entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public E findOne(ID id) {
        E entity = em.find(entityClass, id);
        return entity;
    }

    public List<E> findAll() {
        List<E> entityList = em.createQuery(em.getCriteriaBuilder().createQuery(entityClass)).getResultList();
        return entityList;
    }
}
