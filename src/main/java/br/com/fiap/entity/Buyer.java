package br.com.fiap.entity;

import br.com.fiap.enums.CartStatus;
import br.com.fiap.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Cart> cartList = new ArrayList<>();

    public Buyer(String name, LocalDate birthDate, Address address, Cart cart) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        addCart(cart);
    }

    public void addCart(Cart cart){
        if(cartList.stream().noneMatch(c -> c.getStatus().equals(CartStatus.OPEN))){
                cart.setBuyer(this);
                cartList.add(cart);
        }else {

            throw new BusinessException("you already have a cart with OPEN status");
        }
    }

    public Cart getCart(){
        return this.getCartList().stream().filter(cart -> cart.getStatus().equals(CartStatus.OPEN)).findFirst().get();
    }


    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate + '\'' +
                ", cart=" + cartList +
                '}';
    }
}
