package emsi.moncef.order.services;

import emsi.moncef.order.dtos.OrderDTO;
import emsi.moncef.order.mappers.OrderMapper;
import emsi.moncef.order.models.Order;
import emsi.moncef.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDtoList(orders);
    }

    
    public ResponseEntity<OrderDTO> getOrderByOrderNumber(String orderNumber) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
        return order.map(value -> ResponseEntity.ok(orderMapper.toDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    
    public ResponseEntity<OrderDTO> updateOrder(String orderNumber, OrderDTO updatedOrderDTO) {
        Optional<Order> existingOrder = orderRepository.findByOrderNumber(orderNumber);
        if (existingOrder.isPresent()) {
            Order updatedOrder = orderMapper.toEntity(updatedOrderDTO);
            updatedOrder.setId(existingOrder.get().getId());
            return ResponseEntity.ok(orderMapper.toDto(orderRepository.save(updatedOrder)));
        }
        return ResponseEntity.notFound().build();
    }

    
    public ResponseEntity<OrderDTO> patchOrder(String orderNumber, OrderDTO updatedOrderDTO) {
        Optional<Order> existingOrder = orderRepository.findByOrderNumber(orderNumber);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            if (updatedOrderDTO.orderNumber() != null) order.setOrderNumber(updatedOrderDTO.orderNumber());
            if (updatedOrderDTO.orderDate() != null) order.setOrderDate(updatedOrderDTO.orderDate());
            if (updatedOrderDTO.customerName() != null) order.setCustomerName(updatedOrderDTO.customerName());
            if (updatedOrderDTO.customerEmail() != null) order.setCustomerEmail(updatedOrderDTO.customerEmail());
            if (updatedOrderDTO.shippingAddress() != null) order.setShippingAddress(updatedOrderDTO.shippingAddress());
            if (updatedOrderDTO.status() != null) order.setStatus(updatedOrderDTO.status());

            return ResponseEntity.ok(orderMapper.toDto(orderRepository.save(order)));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Void> deleteOrder(String orderNumber) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);
        if (order.isPresent()) {
            orderRepository.deleteByOrderNumber(orderNumber);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
