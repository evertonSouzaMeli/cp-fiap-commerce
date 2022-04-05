package br.com.fiap;

import br.com.fiap.dao.impl.BuyerDAOImpl;
import br.com.fiap.entity.Buyer;
import br.com.fiap.entity.Cart;
import br.com.fiap.entity.Product;
import br.com.fiap.entity.Stock;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.service.BuyerService;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws CommitException, IdNotFoundException {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BuyerService buyerService = new BuyerService(new BuyerDAOImpl(entityManager));

        Buyer buyer = new Buyer("João Souza", LocalDate.of(2007, 07, 21), new Cart());

        buyerService.save(buyer);

        System.out.println(buyerService.findById(1) +"\n"+ buyerService.findById(1).getCart().toString());

    }
}
