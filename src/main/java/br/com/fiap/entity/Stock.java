package br.com.fiap.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_STOCK")
@SequenceGenerator(name="stock", sequenceName = "SQ_TB_STOCK", allocationSize = 1)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock")
    private Integer id;

    @Column(name = "nr_size", nullable = false, precision = 3)
    private Integer size;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Product> products;

    public Stock() {

    }

    public Stock(Integer size) {
        this.size = size;
    }
}
