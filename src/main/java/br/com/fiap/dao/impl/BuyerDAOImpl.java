package br.com.fiap.dao.impl;

import br.com.fiap.entity.Buyer;
import br.com.fiap.exception.IdNotFoundException;

import javax.persistence.EntityManager;
import java.util.List;

public class BuyerDAOImpl extends GenericDAOImpl<Integer, Buyer>  {
    public BuyerDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Buyer> findAll() {
        return super.findAll();
    }

    @Override
    public Buyer findById(Integer id) throws IdNotFoundException {
        return super.findById(id);
    }

    @Override
    public void setClazz(Class<Buyer> buyerClass) {
        super.setClazz(buyerClass);
    }
}
