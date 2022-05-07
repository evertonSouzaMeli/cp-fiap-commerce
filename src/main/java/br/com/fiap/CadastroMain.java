package br.com.fiap;

import br.com.fiap.dao.impl.BuyerDAOImpl;
import br.com.fiap.dao.impl.InvoiceDAOImpl;
import br.com.fiap.dao.impl.ProductDAOImpl;
import br.com.fiap.dao.impl.StockDAOImpl;
import br.com.fiap.entity.*;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
            InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl(entityManager);

            /** Cadastrando Comprador, Carrinho, Endereço **/
            //buyerDAO.save(new Buyer("Everton", LocalDate.of(1996, 3, 9), new Address("Rua das Flores", "n°100", "00000000")));

            /** Cadastramento Estoque e Produto **/
            /*Stock stock = new Stock(10);
            stock.addProduct(Arrays.asList(
                             new Product("Ovo", 2, new BigDecimal(0.50)),
                             new Product("Farinha", 2, new BigDecimal(5.50)),
                             new Product("Manteiga", 2, new BigDecimal(7.90)),
                             new Product("Chocolate", 2, new BigDecimal(15.50))));
            stockDAO.save(stock);*/

            /** Adicionando Produto do Estoque ao Carrinho **/
            /*Buyer buyer = buyerDAO.findById(1);
            buyer.getCart().addProduct(stockDAO.findById(2).getProductList().get(2));
            buyerDAO.update(buyer);*/

            /** Criando nota fiscal e Cadastrando **/
            /*Invoice invoice = new Invoice(Invoice.convertFileToByte(Invoice.createInvoiceFile(buyerDAO.findById(1).getCart())));
            invoiceDAO.save(invoice);*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}