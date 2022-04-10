package br.com.fiap.dao.impl;

import br.com.fiap.entity.Buyer;
import javax.persistence.EntityManager;

public class BuyerDAOImpl extends GenericDAOImpl<Integer, Buyer>  {
    public BuyerDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
