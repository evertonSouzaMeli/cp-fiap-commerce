package br.com.fiap.service;

import br.com.fiap.dao.impl.BuyerDAOImpl;
import br.com.fiap.entity.Buyer;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;
import java.util.List;

public class BuyerService {
    private BuyerDAOImpl buyerDAO;

    public BuyerService(BuyerDAOImpl buyerDAO){
        this.buyerDAO = buyerDAO;
    }

    public List<Buyer> findAll() {
        return buyerDAO.findAll();
    }

    public Buyer findById(Integer id) throws IdNotFoundException {
         return (Buyer) buyerDAO.findById(id);
    }

    public void save(Buyer buyer) throws CommitException {
        buyerDAO.save(buyer);
    }

    public void update(Buyer buyer) throws CommitException {
        buyerDAO.update(buyer);
    }

    public void delete(Buyer buyer) throws CommitException {
        buyerDAO.delete(buyer);
    }
}
