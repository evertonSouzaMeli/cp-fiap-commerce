package br.com.fiap.dao.impl;

import br.com.fiap.entity.Buyer;
import br.com.fiap.entity.Cart;
import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.CommitException;

import javax.persistence.EntityManager;

public class BuyerDAOImpl extends GenericDAOImpl<Integer, Buyer>  {
    public BuyerDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(Buyer object) throws CommitException {
        Cart cart = object.getCarts().stream().filter(x -> x.getStatus() == CartStatus.OPEN).findFirst().get();
        cart.removeProduct(cart.getProducts());
        super.delete(object);
    }
}
