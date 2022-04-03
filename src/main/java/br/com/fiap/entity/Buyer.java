package br.com.fiap.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_BUYER")
@SequenceGenerator(name="buyer", sequenceName = "SQ_TB_BUYER", allocationSize = 1)
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buyer")
    private Integer id;

    @Column(name = "nm_buyer", nullable = false, length = 50)
    private String name;

    @Temporal(TemporalType.TIME)
    @Column(name = "dt_birth_date", nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Cart> cart;

    public Buyer() {}

    public Buyer(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
