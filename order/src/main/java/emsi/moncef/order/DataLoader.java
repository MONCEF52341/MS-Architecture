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

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final Faker faker = new Faker();
    private final Random random = new Random();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public void run(String... args) {
        try {
            loadOrders();
        } catch (Exception e) {
            log.error("Error loading data: {}", e.getMessage());
            System.exit(1);
        }
    }

    private void loadOrders() {
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
                OrderLine orderLine = OrderLine.builder()
                        .skuCode("PRD-" + faker.random().hex(8))
                        .quantity(random.nextLong(100) + 1)
                        .order(order)
                        .build();

                orderLineRepository.save(orderLine);
            }

            log.info("Order with order number {} and {} order lines created.", orderNumber, numOrderLines);
        }
    }
}
