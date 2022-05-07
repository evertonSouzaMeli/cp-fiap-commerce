package br.com.fiap.entity;

import br.com.fiap.dao.impl.ProductDAOImpl;
import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.BusinessException;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "TB_CART")
@SequenceGenerator(name="cart", sequenceName = "SQ_TB_CART", allocationSize = 1)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nm_status", nullable = false)
    private CartStatus status;

    @Column(name = "dt_creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "nr_total", precision = 6, scale = 2, nullable = false)
    private BigDecimal total;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JoinTable(name = "TB_CART_PRODUCT")
    private List<Product> productList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Cart(){
        this.status = CartStatus.OPEN;
        this.total = BigDecimal.ZERO;
        this.creationDateTime = LocalDateTime.now();
    }

    public void addProduct(Product product){
        product.getCartList().add(this);
        product.getStock().removeProduct(product);
        setTotal(getTotal().add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))));
        productList.add(product);
    }

    public void addProduct(List<Product> productList){
        productList.forEach(this::addProduct);
    }

    public void removeProduct(Product product){
        Integer size = product.getStock().getSize();
        Integer usableSpace = product.getStock().getUsableSpace();

        if(usableSpace < size) {
            setTotal(getTotal().subtract(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))));
            product.getStock().addProduct(product);
            this.getProductList().remove(product);
        }else {
            throw new BusinessException("Stock has reached the limit");
        }
    }

    public void removeProduct(List<Product> productList){
        productList.forEach(this::removeProduct);
    }

    public void closeCart() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ProductDAOImpl productDAO = new ProductDAOImpl(entityManager);

        this.setStatus(CartStatus.CLOSED);
        this.getProductList().forEach(product -> {
            try {
                productDAO.delete(product);
            } catch (CommitException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public String toString() {
        return "Cart{" + "id=" + id +
                ", status=" + status +
                ", creationDateTime=" + creationDateTime +
                ", total=" + total +
                ", buyerId=" + (buyer.equals(null) ? "null" : buyer.getId()) +
                '}';
    }
}
