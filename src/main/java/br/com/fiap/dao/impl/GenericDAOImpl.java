package br.com.fiap.dao.impl;

import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDAOImpl<R, T> implements GenericDAO<R, T> {
    protected EntityManager entityManager;
    private Class<T> clazz;

    public GenericDAOImpl(EntityManager entityManager) {
        try {
            this.entityManager = entityManager;
            this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll() {
       return entityManager.createQuery("Select t from " + clazz.getSimpleName() + " t").getResultList();
    }

    @Override
    public T findById(R id) throws IdNotFoundException {
        return entityManager.find(clazz, id);
    }

    @Override
    public void save(T object) throws CommitException {
        entityManager.persist(object);
        commit();
    }

    @Override
    public void update(T object) throws CommitException {
        entityManager.merge(object);
        commit();
    }

    @Override
    public void delete(T object) throws CommitException {
        entityManager.remove(object);
        commit();
    }

    @Override
    public void commit() throws CommitException {
        try {
            entityManager.getTransaction().begin();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new CommitException("Something bad happened doing rollback".toUpperCase(), e.getCause());
        }
    }

    public void setClazz(Class<T> t){
        clazz = t;
    }
}
