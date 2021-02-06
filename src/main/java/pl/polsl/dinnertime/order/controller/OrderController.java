package pl.polsl.dinnertime.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.order.service.OrderService;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.service.OrderRecordService;

import java.util.List;

@RestController
@CrossOrigin
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
    public ResponseEntity<OrderInfo> createOrder(@RequestBody NewOrderRequest newOrderRequest) {
        Order newOrder = orderService.createOrder(newOrderRequest);
        return ResponseEntity.ok(new OrderInfo(newOrder));
    }

    @PostMapping("joinToOrder")
    public void jointToOrder(@RequestBody OrderRecordRequest orderRecordRequest) {
        if(orderRecordRequest.getCoupon()!=null&& orderRecordRequest.getCoupon()){
            if(!orderService.takeOverOrder(orderRecordRequest.getOrderId()))
                return;
            orderRecordService.createOrderRecord(orderRecordRequest);
        }else{
            orderService.joinToOrder(orderRecordRequest.getOrderId());
            orderRecordService.createOrderRecord(orderRecordRequest);
        }
    }

}
