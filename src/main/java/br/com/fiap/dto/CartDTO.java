package br.com.fiap.dto;

import br.com.fiap.entity.Cart;
import br.com.fiap.enums.CartStatus;
import lombok.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CartDTO {
    private CartStatus status;
    private Date creationDateTime;

    @Min(value = 1, message = "Price must be greater than 1")
    private BigDecimal total;
    private BuyerDTO buyer;
    private List<ProductDTO> products;

    public static CartDTO toDTO(Cart cart){
        return CartDTO.builder()
                .status(cart.getStatus())
                .creationDateTime(cart.getCreationDateTime())
                .total(cart.getTotal())
                .buyer(BuyerDTO.toDTO(cart.getBuyer()))
                .products(ProductDTO.listEntityToDTO(cart.getProducts()))
                .build();
    }

    public static Cart toEntity(CartDTO cartDTO){
        return Cart.builder()
                .status(cartDTO.getStatus())
                .creationDateTime(cartDTO.getCreationDateTime())
                .total(cartDTO.getTotal())
                .buyer(BuyerDTO.toEntity(cartDTO.getBuyer()))
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
