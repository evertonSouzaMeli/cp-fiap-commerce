package br.com.fiap.service;

import br.com.fiap.dao.impl.StockDAOImpl;
import br.com.fiap.dto.StockDTO;
import br.com.fiap.entity.Stock;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

import java.util.List;

public class StockService {

    private StockDAOImpl stockDAO;

    public StockService(StockDAOImpl stockDAO){
        this.stockDAO = stockDAO;
    }

    public List<Stock> findAll() {
        return stockDAO.findAll();
    }

    public Stock findById(Integer id) throws IdNotFoundException {
        return stockDAO.findById(id);
    }

    public void save(StockDTO stockDTO) throws CommitException {
        Stock stock = StockDTO.toEntity(stockDTO);
        stockDAO.save(stock);
    }

    public void update(StockDTO stockDTO) throws CommitException {
        Stock stock = StockDTO.toEntity(stockDTO);
        stockDAO.update(stock);
    }

    public void delete(StockDTO stockDTO) throws CommitException {
        Stock stock = StockDTO.toEntity(stockDTO);
        stockDAO.delete(stock);
    }
}
