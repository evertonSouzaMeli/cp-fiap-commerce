package br.com.fiap.entity;

import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "cart")
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Cart(){
        this.status = CartStatus.OPEN;
        this.total = BigDecimal.ZERO;
        this.creationDateTime = LocalDateTime.now();
    }

    public void addProduct(Product product){
        product.setCart(this);
        product.getStock().removeProduct(product);
        setTotal(getTotal().add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))));
        products.add(product);
    }

    public void addProduct(List<Product> productList){
        productList.forEach(this::addProduct);
    }

    public void removeProduct(Product product){
        Integer size = product.getStock().getSize();
        Integer usableSpace = product.getStock().getUsableSpace();

        if(usableSpace < size) {
            setTotal(getTotal().subtract(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))));
            product.setCart(null);
            product.getStock().addProduct(product);
            products.remove(product);
        }else {
            throw new BusinessException("Stock has reached the limit");
        }
    }

    public void removeProduct(List<Product> productList){
        productList.forEach(this::removeProduct);
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
