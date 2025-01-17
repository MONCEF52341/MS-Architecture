package emsi.moncef.order.controllers;

import emsi.moncef.order.dtos.OrderDTO;
import emsi.moncef.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDTO> getOrderByOrderNumber(@PathVariable String orderNumber) {
        return orderService.getOrderByOrderNumber(orderNumber);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(201).body(createdOrder);
    }


    @PutMapping("/{orderNumber}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String orderNumber, @RequestBody OrderDTO updatedOrderDTO) {
        return orderService.updateOrder(orderNumber, updatedOrderDTO);
    }


    @PatchMapping("/{orderNumber}")
    public ResponseEntity<OrderDTO> patchOrder(@PathVariable String orderNumber, @RequestBody OrderDTO updatedOrderDTO) {
        return orderService.patchOrder(orderNumber, updatedOrderDTO);
    }


    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderNumber) {
        return orderService.deleteOrder(orderNumber);
    }
}
