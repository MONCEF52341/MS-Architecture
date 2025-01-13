package emsi.moncef.order;

import com.github.javafaker.Faker;
import emsi.moncef.order.enums.Status;
import emsi.moncef.order.models.Order;
import emsi.moncef.order.models.OrderLine;
import emsi.moncef.order.repositories.OrderLineRepository;
import emsi.moncef.order.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private static final String SKU_CODES_API_URL = "http://localhost:8081/api/products/skucodes";
    private final Faker faker = new Faker();
    private final Random random = new Random();
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public void run(String... args) {
        try {
            List<String> skuCodes = fetchSkuCodes();
            if (skuCodes.isEmpty()) {
                log.warn("No SKU codes found. Skipping order creation.");
                return;
            }
            loadOrders(skuCodes);
        } catch (Exception e) {
            log.error("Error loading data: {}", e.getMessage());
            System.exit(1);
        }
    }

    private List<String> fetchSkuCodes() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(SKU_CODES_API_URL, List.class);
        } catch (Exception e) {
            log.error("Failed to fetch SKU codes from API: {}", e.getMessage());
            return List.of(); // Retourner une liste vide si l'API échoue
        }
    }

    private void loadOrders(List<String> skuCodes) {
        for (int i = 0; i < 50; i++) {
            String orderNumber = "ORD-" + faker.random().hex(8);

            Order order = Order.builder()
                    .orderNumber(orderNumber)
                    .orderDate(LocalDateTime.now().minusDays(random.nextInt(30)))
                    .customerName(faker.name().fullName())
                    .customerEmail(faker.internet().emailAddress())
                    .shippingAddress(faker.address().fullAddress())
                    .status(Status.values()[random.nextInt(Status.values().length)])
                    .build();

            orderRepository.save(order);

            int numOrderLines = random.nextInt(10) + 1;
            for (int j = 0; j < numOrderLines; j++) {
                // Choisir un SKU aléatoire depuis les SKU récupérés
                String skuCode = skuCodes.get(random.nextInt(skuCodes.size()));

                OrderLine orderLine = OrderLine.builder()
                        .skuCode(skuCode)
                        .quantity(random.nextLong(100) + 1)
                        .order(order)
                        .build();

                orderLineRepository.save(orderLine);
            }

            log.info("Order with order number {} and {} order lines created.", orderNumber, numOrderLines);
        }
    }
}
