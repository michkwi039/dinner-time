package pl.polsl.dinnertime.orderRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.service.OrderRecordService;

@RestController
public class OrderRecordController {

    private final OrderRecordService orderRecordService;

    @Autowired
    public OrderRecordController(OrderRecordService orderRecordService) {
        this.orderRecordService = orderRecordService;
    }

    @PostMapping
    public void createOrderRecord(@RequestBody OrderRecordRequest orderRecordRequest){
        orderRecordService.createOrderRecord(orderRecordRequest);
    }
}
