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

public class Main {
    public static void main(String[] args) throws CommitException, IdNotFoundException {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Buyer buyer = new Buyer();
        Cart cart = new Cart();
        Stock stock = new Stock();
        Product product = new Product();
    }
}
