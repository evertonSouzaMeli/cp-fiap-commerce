package br.com.fiap;

import br.com.fiap.dao.impl.*;
import br.com.fiap.entity.*;
import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws CommitException {
        try {
            EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            //Criação DAOs
            CartDAOImpl cartDAO = new CartDAOImpl(entityManager);
            BuyerDAOImpl buyerDAO = new BuyerDAOImpl(entityManager);

            //Criação de Buyer & Cart
            Cart cart = new Cart();

            //Cadastro cart
            cartDAO.save(cart);

            //Maneira tradicional
            Buyer evertonBuyer = new Buyer("Everton Souza", LocalDate.of(1996, 3, 9), cartDAO.findById(1));

            //Por relacionamento
            Buyer joaoBuyer = new Buyer("João Souza", LocalDate.of(2007, 7, 21), new Cart());

            //Cadastro buyer
            buyerDAO.save(evertonBuyer);
            buyerDAO.save(joaoBuyer);
            System.out.println("[BUYER] Primeira consulta\n" + buyerDAO.findAll());

            //Atualização buyer
            Buyer novoBuyer = buyerDAO.findById(1);
            novoBuyer.setName("Maria");
            novoBuyer.setBirthDate(LocalDate.of(1991, 8, 1));
            buyerDAO.update(novoBuyer);
            System.out.println("\n[BUYER] Segunda consulta\n" + buyerDAO.findAll());

            //Remoção buyer & cart atrelado
            buyerDAO.delete(joaoBuyer);
            System.out.println("\n[BUYER | CART] Terceira consulta\n" + buyerDAO.findAll());

            //Comprovação cart removido com o buyer
            System.out.println("\n[CART] Quarta consulta" + cartDAO.findAll());


            System.out.println("\n ============================================================================= \n");

            //Criacao DAOs
            StockDAOImpl stockDAO = new StockDAOImpl(entityManager);
            ProductDAOImpl productDAO = new ProductDAOImpl(entityManager);

            //Criação do produto
            Product product = new Product("Leite", 1, new BigDecimal("3.80"));
            Product product2 = new Product("Café", 1, new BigDecimal("5.99"));

            //cadastro do produto
            productDAO.save(product);
            productDAO.save(product2);
            System.out.println("[PRODUCT] Primeira consulta\n" + productDAO.findAll());

            //atualização de um produto
            product.setPrice(new BigDecimal("4.80"));
            System.out.println("\n[PRODUCT] Segunda consulta\n" + productDAO.findAll());

            //Remoção produto
            productDAO.delete(product2);
            System.out.println("\n[PRODUCT] Terceira consulta\n" + productDAO.findAll());

            System.out.println("\n ============================================================================= \n");

            //Criacao stock e adição de produtos no momento da criação
            Stock stock = new Stock(5, productDAO.findById(1));

            //Criação de um novo estoque com vários produtos
            Stock stock2 = new Stock(10);

            List<Product> products = Arrays.asList(new Product("Pão", 3, new BigDecimal("0.70")),
                    new Product("Manteiga", 2, new BigDecimal("7.80")));
            stock2.addProduct(products);

            //Cadastro dos estoques com respectivos produtos
            stockDAO.save(stock);
            stockDAO.save(stock2);
            System.out.println("[STOCK] Primeira consulta\n" + stockDAO.findAll());

            //Atualização estoque
            stock.setSize(10);
            stockDAO.update(stockDAO.findById(1));
            System.out.println("\n[STOCK] Segunda consulta\n" + stockDAO.findAll());

            //Remoção de estoque
            stockDAO.update(stockDAO.findById(1));
            System.out.println("\n[STOCK] Terceira consulta\n" + stockDAO.findAll());

            //Comprovação que remoção do estoque apaga também produto que não estiver num carrinho
            stockDAO.delete(stock);
            System.out.println("\n[STOCK] Quarta consulta\n" + stockDAO.findAll());
            System.out.println("\n[PRODUCT] Quinta consulta\n" + productDAO.findAll());

            System.out.println("\n ============================================================================= \n");

            //Associação produtos com carrinho
            novoBuyer.getCarts().stream().filter(x -> x.getStatus().equals(CartStatus.OPEN)).findFirst().get().addProduct(products);
            buyerDAO.update(novoBuyer);

            System.out.println("[BUYER] Primeira consulta\n" + buyerDAO.findById(novoBuyer.getId()));

            System.out.println("\n[CART] Primeira consulta\n" + buyerDAO.findById(novoBuyer.getId())
                    .getCarts().stream()
                    .filter(c -> c.getStatus().equals(CartStatus.OPEN))
                    .findFirst()
                    .get()
                    .getProducts());



            InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl(entityManager);

            //Criação da nota fiscal
            Invoice invoice = new Invoice(Invoice.convertFileToByte(Invoice.createInvoiceFile(cart)));


            //Cadastro da nota fiscal (bytes) no banco de dados [upload]
            invoiceDAO.save(invoice);

            //Obtenção da nota fiscal e conversão em arquivo txt [download]
            Invoice.convertByteToFile(invoiceDAO.findById(1).getData());

            //remoção do banco de dados
            invoiceDAO.delete(invoice);
            System.out.println(invoiceDAO.findAll());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
