package br.com.fiap;

import br.com.fiap.dao.impl.BuyerDAOImpl;
import br.com.fiap.dto.BuyerDTO;
import br.com.fiap.entity.Buyer;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.service.BuyerService;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

public class BuyerMain {
    public static void main(String[] args) throws CommitException, IdNotFoundException {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BuyerService buyerService = new BuyerService(new BuyerDAOImpl(entityManager));

        //Buyer section
        BuyerDTO buyerDTO = new BuyerDTO("Everton Souza", LocalDate.of(1996, 3, 9));

        //Cadastrando um Buyer
        buyerService.save(buyerDTO);

        //Procurando Todos Buyers
        System.out.println(buyerService.findAll());

        //Procurando um Buyer
        Buyer buyer = buyerService.findById(1);

        //Atualizando Buyer
        buyer.setName("João Souza");
        buyerService.update(BuyerDTO.toDTO(buyer));
        System.out.println(buyerService.findById(1));

        //Deletando Buyer
        buyerService.delete(BuyerDTO.toDTO(buyer));
        System.out.println(buyerService.findById(1));
    }
}
