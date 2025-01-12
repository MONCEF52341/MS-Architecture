package emsi.moncef.product.mappers;

import emsi.moncef.product.dtos.ProductDTO;
import emsi.moncef.product.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getSkuCode(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory(),
                product.getManufacturer(),
                product.isAvailable()
        );
    }

    public List<ProductDTO> toDtoList(List<Product> students) {
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
