package emsi.moncef.order.mappers;

import emsi.moncef.order.dtos.OrderDTO;
import emsi.moncef.order.dtos.OrderLineDTO;
import emsi.moncef.order.models.Order;
import emsi.moncef.order.models.OrderLine;
import emsi.moncef.order.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private final OrderRepository orderRepository;

    public OrderMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO toDto(Order order) {
        List<OrderLineDTO> orderLineDTOs = order.getOrderLines().stream()
                .map(orderLine -> new OrderLineDTO(orderLine.getSkuCode(), orderLine.getQuantity(), orderLine.getOrder().getId()))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getOrderNumber(),
                order.getOrderDate(),
                order.getCustomerName(),
                order.getCustomerEmail(),
                order.getShippingAddress(),
                order.getStatus(),
                orderLineDTOs
        );
    }

    public List<OrderDTO> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderNumber(dto.orderNumber());
        order.setOrderDate(dto.orderDate());
        order.setCustomerName(dto.customerName());
        order.setCustomerEmail(dto.customerEmail());
        order.setShippingAddress(dto.shippingAddress());
        order.setStatus(dto.status());
        if (dto.orderLines() != null) {
            List<OrderLine> orderLines = dto.orderLines().stream()
                    .map(orderLineDTO -> new OrderLine(
                            null,
                            orderLineDTO.skuCode(),
                            orderLineDTO.quantity(),
                            order
                    ))
                    .collect(Collectors.toList());

            order.setOrderLines(orderLines);
        }

        return order;
    }

    public List<Order> toEntitiesList(List<OrderDTO> orderDTOList) {
        return orderDTOList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
