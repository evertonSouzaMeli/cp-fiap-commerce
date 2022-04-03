package br.com.fiap.dao.impl;

import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAOImpl<R extends Number, T> implements GenericDAO<R, T> {
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
    public List<T> findAll(Class<T> tClass) {
       return entityManager.createQuery("Select t from " + tClass.getSimpleName() + " t").getResultList();
    }

    @Override
    public T findById(R id) throws IdNotFoundException {
        Optional<T> entity = (Optional<T>) entityManager.find(clazz, id);
        return entity.orElseThrow(() -> new IdNotFoundException(String.format("Id {} not found", id).toUpperCase()));
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
}
