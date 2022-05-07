package br.com.fiap.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "TB_ADDRESS")
@SequenceGenerator(name="buyer", sequenceName = "SQ_TB_BUYER", allocationSize = 1)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address")
    private Integer id;

    @Column(name = "nm_street", length = 100, nullable = false)
    private String street;

    @Column(name = "nm_city", length = 100, nullable = false)
    private String city;

    @Column(name = "nb_zip_code", precision = 8, nullable = false)
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Buyer buyer;

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
