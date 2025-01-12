package emsi.moncef.order.dtos;

import emsi.moncef.order.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        String orderNumber,
        LocalDateTime orderDate,
        String customerName,
        String customerEmail,
        String shippingAddress,
        Status status,
        List<OrderLineDTO> orderLines

) {
}
