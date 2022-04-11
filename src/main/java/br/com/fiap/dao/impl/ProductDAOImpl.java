package br.com.fiap.dao.impl;

import br.com.fiap.entity.Product;
import javax.persistence.EntityManager;

public class ProductDAOImpl extends GenericDAOImpl<Integer, Product> {
    public ProductDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
