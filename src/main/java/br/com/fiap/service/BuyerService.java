package br.com.fiap.service;

import br.com.fiap.dao.impl.BuyerDAOImpl;
import br.com.fiap.dto.BuyerDTO;
import br.com.fiap.entity.Buyer;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;
import java.util.List;
import java.util.Optional;

public class BuyerService {
    private BuyerDAOImpl buyerDAO;

    public BuyerService(BuyerDAOImpl buyerDAO){
        this.buyerDAO = buyerDAO;
    }

    public List<Buyer> findAll() {
        return buyerDAO.findAll();
    }

    public Buyer findById(Integer id) throws IdNotFoundException {
         return Optional.ofNullable(buyerDAO.findById(id)).orElseThrow( () -> new IdNotFoundException());
    }

    public void save(BuyerDTO buyerDTO) throws CommitException {
        Buyer buyer = BuyerDTO.toEntity(buyerDTO);
        buyerDAO.save(buyer);
    }

    public void update(BuyerDTO buyerDTO) throws CommitException {
        Buyer buyer = BuyerDTO.toEntity(buyerDTO);
        buyerDAO.update(buyer);
    }

    public void delete(BuyerDTO buyerDTO) throws CommitException {
        Buyer buyer = BuyerDTO.toEntity(buyerDTO);
        buyerDAO.delete(buyer);
    }
}
