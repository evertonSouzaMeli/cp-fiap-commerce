package br.com.fiap.dao.impl;

import br.com.fiap.entity.Stock;
import br.com.fiap.exception.CommitException;

import javax.persistence.EntityManager;

public class StockDAOImpl extends GenericDAOImpl<Integer, Stock> {
    private CartDAOImpl cartDAO;

    public StockDAOImpl(EntityManager entityManager) {
        super(entityManager);
        cartDAO = new CartDAOImpl(entityManager);
    }

    @Override
    public void delete(Stock object) throws CommitException {
        object.getProductList().forEach(product -> {
            if (product.getCart() != null) {
                product.getCart().removeProduct(product);
                try {
                    cartDAO.update(cartDAO.findById(product.getCart().getId()));
                } catch (CommitException e) {
                    e.printStackTrace();
                }
            }
        });

        super.delete(object);
    }
}
