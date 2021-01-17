package pl.polsl.dinnertime.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.service.OrderService;

import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderInfo>> getCurrentOrders() {
        return ResponseEntity.ok(orderService.getCurrentOrders());
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody NewOrderRequest newOrderRequest) {
        orderService.createOrder(newOrderRequest);
    }

    @PostMapping("joinToOrder")
    public void jointToOrder(@RequestParam Long orderId) {
        orderService.joinToOrder(orderId);
    }

}
