package emsi.moncef.product.dtos;

import emsi.moncef.product.enums.Category;
import emsi.moncef.product.enums.Manufacturer;

import java.math.BigDecimal;

public record ProductDTO(
        String skuCode,
        String name,
        String description,
        BigDecimal price,
        int stockQuantity,
        Category category,
        Manufacturer manufacturer,
        boolean isAvailable
) {
}
