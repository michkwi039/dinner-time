package pl.polsl.dinnertime.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.order.service.OrderService;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecordRepository;
import pl.polsl.dinnertime.orderRecord.service.OrderRecordService;

import java.time.ZonedDateTime;
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
//        List<Order> orderInfos=orderService.getCurrentOrders();
        return ResponseEntity.ok(orderService.getCurrentOrders());
    }

    @PostMapping("orders")
    public void createOrder(@RequestBody NewOrderRequest newOrderRequest) {
        Order order =orderService.createOrder(newOrderRequest);
//        OrderRecordRequest orderRecordRequest= new OrderRecordRequest(newOrderRequest.getMenuPositions(),newOrderRequest.getPrice(),order.getOrderId());
//        //orderRecordRequest.setOrderId(order.getId());
//        OrderRecord orderRecord=orderRecordService.createOrderRecord(orderRecordRequest);
//        order.addOrderRecord(orderRecord);
//        orderService.updateOrder(order);
    }
    @GetMapping("test")
    public void createOrderTest() {
        NewOrderRequest a=new NewOrderRequest();
        a.setTime(ZonedDateTime.now());
        a.setRestaurant("piwniczka");
        a.setCollectingPlace("dom");
        a.setMenuPositions("aaaaaa");
        a.setPrice(12.0);
        Order order =orderService.createOrder(a);
        OrderRecordRequest orderRecordRequest= new OrderRecordRequest(a.getMenuPositions(),a.getPrice(),order.getOrderId());
        //orderRecordRequest.setOrderId(order.getId());
        //OrderRecord orderRecord=orderRecordService.createOrderRecord(orderRecordRequest);
//        orderRecordRequest.setOrderId(order.getId());
//        OrderRecord orderRecord=orderRecordService.createOrderRecord(orderRecordRequest);
//        order.addOrderRecord(orderRecord);
//        orderService.updateOrder(order);
    }

    @PostMapping("joinToOrder")
    public void jointToOrder(@RequestParam Long orderId,@RequestParam String menuPositions,@RequestParam Double price) {
        orderService.joinToOrder(orderId,menuPositions,price);
        OrderRecordRequest orderRecordRequest= new OrderRecordRequest(menuPositions,price,orderId);
        OrderRecord orderRecord=orderRecordService.createOrderRecord(orderRecordRequest);
    }

}
