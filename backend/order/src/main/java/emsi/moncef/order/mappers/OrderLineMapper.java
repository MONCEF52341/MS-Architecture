package emsi.moncef.order.mappers;

import emsi.moncef.order.dtos.OrderLineDTO;
import emsi.moncef.order.models.Order;
import emsi.moncef.order.models.OrderLine;
import emsi.moncef.order.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderLineMapper {

    private final OrderRepository orderRepository;

    public OrderLineMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderLineDTO toDto(OrderLine orderLine) {
        return new OrderLineDTO(
                orderLine.getSkuCode(),
                orderLine.getQuantity(),
                orderLine.getOrder().getId()
        );
    }

    public List<OrderLineDTO> toDtoList(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public OrderLine toEntity(OrderLineDTO dto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setSkuCode(dto.skuCode());
        orderLine.setQuantity(dto.quantity());
        Order order = orderRepository.findById(dto.orderId())
                .orElseThrow(() -> new RuntimeException("No Order with ID: " + dto.orderId()));
        orderLine.setOrder(order);
        return orderLine;
    }

    public List<OrderLine> toEntitiesList(List<OrderLineDTO> orderLineDTOList) {
        return orderLineDTOList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
