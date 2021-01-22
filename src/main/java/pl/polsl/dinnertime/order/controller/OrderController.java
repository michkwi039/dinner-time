package pl.polsl.dinnertime.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.service.OrderService;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.service.OrderRecordService;

import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderRecordService orderRecordService;

    @Autowired
    public OrderController(OrderService orderService,OrderRecordService orderRecordService) {
        this.orderService = orderService;
        this.orderRecordService=orderRecordService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderInfo>> getCurrentOrders() {
        return ResponseEntity.ok(orderService.getCurrentOrders());
    }

    @PostMapping("orders")
    public void createOrder(@RequestBody NewOrderRequest newOrderRequest,@RequestBody OrderRecordRequest orderRecordRequest) {
        orderService.createOrder(newOrderRequest);
        orderRecordService.createOrderRecord(orderRecordRequest);
    }

    @PostMapping("joinToOrder")
    public void jointToOrder(@RequestParam Long orderId) {
        orderService.joinToOrder(orderId);
    }

}
