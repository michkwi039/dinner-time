package pl.polsl.dinnertime.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.service.OrderService;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.service.OrderRecordService;

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
    public void createOrder(@RequestBody NewOrderRequest newOrderRequest) {
        orderService.createOrder(newOrderRequest);
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
