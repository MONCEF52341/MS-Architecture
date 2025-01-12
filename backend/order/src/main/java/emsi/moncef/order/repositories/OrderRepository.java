package emsi.moncef.order.repositories;

import emsi.moncef.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);

    void deleteByOrderNumber(String orderNumber);
}
