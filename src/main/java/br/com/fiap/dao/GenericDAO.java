package br.com.fiap.dao;

import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

import java.util.List;

public interface GenericDAO<R extends Number, T> {
    List<T> findAll(Class<T> object);

    T findById(R id) throws IdNotFoundException;

    void save(T object) throws CommitException;

    void update(T object) throws CommitException;

    void delete(T object) throws CommitException;

    void commit() throws CommitException;
}
