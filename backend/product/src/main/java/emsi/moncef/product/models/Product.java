package emsi.moncef.product.models;

import emsi.moncef.product.enums.Category;
import emsi.moncef.product.enums.Manufacturer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_tbl")
@Builder
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String skuCode;

    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;
    private boolean isAvailable;
}
