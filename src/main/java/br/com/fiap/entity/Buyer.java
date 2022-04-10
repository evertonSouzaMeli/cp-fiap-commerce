package br.com.fiap.entity;

import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "TB_BUYER")
@SequenceGenerator(name="buyer", sequenceName = "SQ_TB_BUYER", allocationSize = 1)
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buyer")
    private Integer id;

    @Column(name = "nm_buyer", nullable = false, length = 50)
    private String name;

    @Column(name = "dt_birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    public Buyer() {}

    public Buyer(String name, LocalDate birthDate, Cart cart) {
        this.name = name;
        this.birthDate = birthDate;
        addCart(cart);
    }

    public void addCart(Cart cart){
        if(carts.stream().noneMatch(x -> x.getStatus().equals(CartStatus.OPEN))){
                cart.setBuyer(this);
                carts.add(cart);
        }else {
            throw new BusinessException("you already have a cart with OPEN status");
        }
    }


    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate + '\'' +
                ", cart=" + carts +
                '}';
    }
}
