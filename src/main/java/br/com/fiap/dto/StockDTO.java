package br.com.fiap.dto;

import br.com.fiap.entity.Stock;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StockDTO {
    @Min(value = 1, message = "Size must be greater than 1")
    private Integer size;

    private List<ProductDTO> products = new ArrayList<>();

    public static Stock toEntity(StockDTO stockDTO){
        return Stock.builder()
                .size(stockDTO.getSize())
                .products(ProductDTO.listDTOToEntity(stockDTO.getProducts()))
                .build();
    }

    public static StockDTO toDTO(Stock stock){
        return StockDTO.builder()
                .size(stock.getSize())
                .products(ProductDTO.listEntityToDTO(stock.getProducts()))
                .build();
    }
}
