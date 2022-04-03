package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

public class CartDAOImpl extends GenericDAOImpl{
    public CartDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
