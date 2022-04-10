package br.com.fiap.dao.impl;

import br.com.fiap.entity.Cart;
import br.com.fiap.entity.Stock;
import br.com.fiap.exception.CommitException;

import javax.persistence.EntityManager;

public class CartDAOImpl extends GenericDAOImpl<Integer, Cart>{
    public CartDAOImpl(EntityManager entityManager) { super(entityManager); }

    @Override
    public void delete(Cart object) throws CommitException {
        object.getProducts().forEach(object::removeProduct);
        super.delete(object);
    }
}
