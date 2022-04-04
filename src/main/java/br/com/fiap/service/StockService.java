package br.com.fiap.service;

import br.com.fiap.dao.impl.StockDAOImpl;

public class StockService {

    private StockDAOImpl stockDAO;

    public StockService(StockDAOImpl stockDAO){
        this.stockDAO = stockDAO;
    }
}
