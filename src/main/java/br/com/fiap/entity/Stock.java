package br.com.fiap.entity;

import br.com.fiap.exception.BusinessException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "TB_STOCK")
@SequenceGenerator(name="stock", sequenceName = "SQ_TB_STOCK", allocationSize = 1)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock")
    private Integer id;

    @Column(name = "nr_size", nullable = false, precision = 3)
    private Integer size;

    @Column(name = "nr_usableSpace", nullable = false)
    private Integer usableSpace;

    @OneToMany(mappedBy = "stock", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Product> productList = new ArrayList<>();

    public Stock() {}

    public Stock(Integer size) {
        this.size = size;
        this.usableSpace = size;
    }
    public Stock(Integer size, Product product) {
        this(size);
        addProduct(product);
    }

    public void addProduct(Product product){
        if(usableSpace > 0) {
            usableSpace -= product.getQuantity();
            product.setStock(this);
            productList.add(product);
        }else {
            throw new BusinessException("The stock no longer has usable space");
        }
    }

    public void removeProduct(Product product){
        if(usableSpace < size) {
            usableSpace += product.getQuantity();
            productList.remove(product);
        }else {
            throw new BusinessException("Stock has reached the limit");
        }
    }

    private void setUsableSpace(Integer usableSpace){
        this.usableSpace = usableSpace;
    }

    public void addProduct(Product... product){ productList.forEach(this::addProduct); }

    public void addProduct(List<Product> productList){ productList.forEach(this::addProduct); }

    public void setSize(Integer size) {
        Integer usableSpace = getUsableSpace();
        if(size >= usableSpace) {
            Integer diff = size - this.size;
            this.size = size;
            setUsableSpace(usableSpace + diff);
        }else {
            throw new BusinessException("You cannot decrease the size of the stock more than the usable space");
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", size=" + size +
                ", usableSpace=" + usableSpace +
                '}';
    }
}
