package br.com.fiap;

import br.com.fiap.dao.impl.*;
import br.com.fiap.entity.*;
import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;
import org.hibernate.mapping.Any;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BuscaMain {
    public static void main(String[] args) {
        try {
            EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            BuyerDAOImpl buyerDAO = new BuyerDAOImpl(entityManager);
            StockDAOImpl stockDAO = new StockDAOImpl(entityManager);
            InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl(entityManager);

            Stock stock = stockDAO.findById(2);
            Buyer buyer = buyerDAO.findById(1);

            /**Obter Comprador **/
            System.out.println(buyer);

            /** Obter Carrinho a partir do Comprador **/
            System.out.println(buyer.getCart());

            /** Obter Endereço a partir do Comprador **/
            System.out.println(buyer.getAddress());

            /** Obter Estoque **/
            System.out.println(stock);

            /** Obter Produtos a partir do Estoque **/
            System.out.println(stock.getProductList());

            /** Obter Produtos a partir Carrinho **/
            System.out.println(buyer.getCart().getProductList());

            /** Obter Nota Fiscal e transformar em String **/
            System.out.println("\n" + new String(invoiceDAO.findById(1).getData()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
