package br.com.fiap.dto;

import br.com.fiap.entity.Cart;
import br.com.fiap.enums.CartStatus;
import lombok.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class CartDTO {
    private CartStatus status;
    private LocalDate creationDateTime;

    @Min(value = 1, message = "Price must be greater than 1")
    private BigDecimal total;
    private List<ProductDTO> products = new ArrayList<>();

    public CartDTO() {
        this.status = CartStatus.OPEN;
        this.creationDateTime = LocalDate.now();
        this.total = BigDecimal.ZERO;
    }

    public static CartDTO toDTO(Cart cart){
        return CartDTO.builder()
                .status(cart.getStatus())
                .creationDateTime(cart.getCreationDateTime())
                .total(cart.getTotal())
                .products(ProductDTO.listEntityToDTO(cart.getProducts()))
                .build();
    }

    public static Cart toEntity(CartDTO cartDTO){
        return Cart.builder()
                .status(cartDTO.getStatus())
                .creationDateTime(cartDTO.getCreationDateTime())
                .total(cartDTO.getTotal())
                .products(ProductDTO.listDTOToEntity(cartDTO.getProducts()))
                .build();
    }

    public static List<Cart> listDTOToEntity(List<CartDTO> cartDTOList){
        return cartDTOList.stream().map(CartDTO::toEntity).collect(Collectors.toList());
    }

    public static List<CartDTO> listEntityToDTO(List<Cart> cartList){
        return cartList.stream().map(CartDTO::toDTO).collect(Collectors.toList());
    }
}
