package br.com.fiap.dao.impl;

import br.com.fiap.entity.Stock;
import javax.persistence.EntityManager;

public class StockDAOImpl extends GenericDAOImpl<Integer, Stock> {
    public StockDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
