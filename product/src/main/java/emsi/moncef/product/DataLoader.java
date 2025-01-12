package emsi.moncef.product;

import com.github.javafaker.Faker;
import emsi.moncef.product.enums.Category;
import emsi.moncef.product.enums.Manufacturer;
import emsi.moncef.product.models.Product;
import emsi.moncef.product.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final Faker faker = new Faker();
    private final Random random = new Random();
    private final Set<String> usedSkuCodes = new HashSet<>();

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        try {
            loadProducts();
        } catch (Exception e) {
            log.error("Error loading products: ", e);
            System.exit(1);
        }
    }

    private void loadProducts() {
        for (int i = 0; i < 100; i++) {
            // Générer un SKU code unique
            String uniqueSkuCode;
            do {
                uniqueSkuCode = "PRD-" + faker.random().hex(8).toUpperCase();
            } while (usedSkuCodes.contains(uniqueSkuCode));
            usedSkuCodes.add(uniqueSkuCode);

            // Créer un produit fictif
            Product product = Product.builder()
                    .skuCode(uniqueSkuCode)
                    .name(faker.commerce().productName())
                    .description(faker.lorem().sentence(10))
                    .price(BigDecimal.valueOf(faker.number().randomDouble(2, 20, 1000000)))
                    .stockQuantity(random.nextInt(100) + 1)
                    .category(Category.values()[random.nextInt(Category.values().length)])
                    .manufacturer(Manufacturer.values()[random.nextInt(Manufacturer.values().length)])
                    .isAvailable(random.nextBoolean())
                    .build();

            // Sauvegarder dans la base de données
            productRepository.save(product);
        }
        log.info("100 products have been successfully loaded into the database.");
    }
}
