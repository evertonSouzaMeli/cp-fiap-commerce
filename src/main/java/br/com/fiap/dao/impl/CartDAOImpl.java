package br.com.fiap.dao.impl;

import br.com.fiap.entity.Cart;
import javax.persistence.EntityManager;

public class CartDAOImpl extends GenericDAOImpl<Integer, Cart>{
    public CartDAOImpl(EntityManager entityManager) { super(entityManager); }
}
