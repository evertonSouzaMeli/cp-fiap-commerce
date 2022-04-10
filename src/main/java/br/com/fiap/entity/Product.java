package br.com.fiap.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_id", referencedColumnName = "id", nullable = false)
    private Stock stock;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    public Product() {}

    public Product(String name, Integer quantity, BigDecimal price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.inclusionDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", inclusionDate=" + inclusionDate +
                '}';
    }
}

