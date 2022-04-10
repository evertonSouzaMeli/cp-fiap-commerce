package br.com.fiap.dto;

import br.com.fiap.entity.Buyer;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BuyerDTO {
    @Pattern(regexp = "[aA-zZ]{3,50}", message = "The name must contain a minimum of 3 characters and a maximum of 50 characters")
    private String name;

    private LocalDate birthDate;

    private List<CartDTO> cart;


    public static BuyerDTO toDTO(Buyer buyer){
        return BuyerDTO.builder()
                .name(buyer.getName())
                .birthDate(buyer.getBirthDate())
                .cart(CartDTO.listEntityToDTO(buyer.getCart()))
                .build();
    }

    public static Buyer toEntity(BuyerDTO buyerDTO){
        return Buyer.builder()
                .name(buyerDTO.getName())
                .birthDate(buyerDTO.getBirthDate())
                .cart(CartDTO.listDTOToEntity(buyerDTO.getCart()))
                .build();
    }
}
