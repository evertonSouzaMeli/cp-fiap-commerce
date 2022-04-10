package br.com.fiap.dto;

import br.com.fiap.entity.Product;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    @Pattern(regexp = "[aA-zZ]{3,50}", message = "The name must contain a minimum of 3 characters and a maximum of 50 characters")
    private String name;

    @Min(value = 1, message = "Quantity must be greater than 1")
    private Integer quantity;

    @Min(value = 1, message = "Price must be greater than 1")
    private BigDecimal price;

    private LocalDate inclusionDate;

    private StockDTO stock;

    private CartDTO cart;

    public static Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .quantity(productDTO.getQuantity())
                .price(productDTO.getPrice())
                .inclusionDate(productDTO.getInclusionDate())
                .stock(StockDTO.toEntity(productDTO.getStock()))
                .cart(CartDTO.toEntity(productDTO.getCart()))
                .build();
    }

    private static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .inclusionDate(product.getInclusionDate())
                .stock(StockDTO.toDTO(product.getStock()))
                .cart(CartDTO.toDTO(product.getCart()))
                .build();
    }

    public static List<ProductDTO> listEntityToDTO(List<Product> productList) {
        return productList.stream().map(ProductDTO::toDTO).collect(Collectors.toList());
    }

    public static List<Product> listDTOToEntity(List<ProductDTO> productDTOList) {
        return productDTOList.stream().map(ProductDTO::toEntity).collect(Collectors.toList());
    }
}
