package br.com.fiap.dao.impl;

import br.com.fiap.entity.Invoice;

import javax.persistence.EntityManager;

public class InvoiceDAOImpl extends GenericDAOImpl<Integer, Invoice> {

    public InvoiceDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
