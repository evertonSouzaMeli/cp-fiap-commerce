package br.com.fiap.entity;

import br.com.fiap.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "TB_PRODUCT")
@SequenceGenerator(name = "product", sequenceName = "SQ_TB_PRODUCT", allocationSize = 1)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product")
    private Integer id;

    @Column(name = "nm_product", nullable = false, length = 50)
    private String name;

    @Column(name = "nr_quantity", nullable = false, precision = 100)
    private Integer quantity;

    @Column(name = "nr_price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Column(name = "dt_inclusion_date", nullable = false)
    private LocalDate inclusionDate;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public Product() {}

    public Product(String name, Integer quantity, BigDecimal price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.inclusionDate = LocalDate.now();
    }

    public void setQuantity(Integer quantity){
        Stock stock = getStock();
        if(stock != null && stock.getUsableSpace() > quantity)
            this.quantity = quantity;
        else
            throw new BusinessException("Quantity of products cannot be greater than the size of the useful space of the stock.");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", inclusionDate=" + inclusionDate +
                ", stock=" + (stock != null ? stock.getId() : "null") +
                ", cart=" + (cart != null ? cart.getId() : "null") +
                '}';
    }
}

