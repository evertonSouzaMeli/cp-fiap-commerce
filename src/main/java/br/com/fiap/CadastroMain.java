package br.com.fiap;

import br.com.fiap.dao.impl.BuyerDAOImpl;
import br.com.fiap.dao.impl.ProductDAOImpl;
import br.com.fiap.dao.impl.StockDAOImpl;
import br.com.fiap.entity.*;
import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class CadastroMain {
    public static void main(String[] args) {
        try {
            EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            BuyerDAOImpl buyerDAO = new BuyerDAOImpl(entityManager);
            StockDAOImpl stockDAO = new StockDAOImpl(entityManager);
            ProductDAOImpl productDAO = new ProductDAOImpl(entityManager);

            Buyer testBuyer = new Buyer("Everton Souza", LocalDate.of(1996, 3, 9),
                    new Address("Rua das Flores", "São Paulo", "01000-000"),
                    new Cart());

            //String name, Integer quantity, BigDecimal price
            Stock stock = new Stock(10);
            stock.addProduct(Arrays.asList(new Product("Leite", 2, new BigDecimal("4.00")),
                    new Product("Chocolate", 2, new BigDecimal("6.50")),
                    new Product("Ovo", 2, new BigDecimal("0.50")),
                    new Product("Farinha", 2, new BigDecimal("5.50"))));

            //stockDAO.save(stock);
            testBuyer.getCart().addProduct(productDAO.findById(1));
            buyerDAO.save(testBuyer);



        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}