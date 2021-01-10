package pl.polsl.dinnertime.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.dinnertime.order.model.Order;
import pl.polsl.dinnertime.order.service.OrderService;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getCurrentOrders() {
        List<Order> currentOrders = orderService.getCurrentOrders();
        return ResponseEntity.ok(currentOrders);
    }
}
