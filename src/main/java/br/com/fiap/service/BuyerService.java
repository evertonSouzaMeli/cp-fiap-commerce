package br.com.fiap.service;

import br.com.fiap.dao.impl.BuyerDAOImpl;

public class BuyerService {

    private BuyerDAOImpl buyerDAO;

    private BuyerService(BuyerDAOImpl buyerDAO){
        this.buyerDAO = buyerDAO;
    }
}
