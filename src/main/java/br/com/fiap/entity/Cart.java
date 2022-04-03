package br.com.fiap.entity;

import br.com.fiap.enums.CartStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_creation_date_time", nullable = true)
    private Date creationDateTime;

    @Column(name = "nr_total", precision = 6, scale = 2, nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id", nullable = false)
    private Buyer buyer;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    public Cart(){
        this.status = CartStatus.OPEN;
        this.total = BigDecimal.ZERO;
        this.products = new ArrayList<>();
    }
}