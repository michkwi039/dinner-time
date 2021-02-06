package pl.polsl.dinnertime.orderRecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordInfo;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.orderRecord.service.OrderRecordService;

import java.util.List;

@RestController
@CrossOrigin
public class OrderRecordController {

    private final OrderRecordService orderRecordService;

    @Autowired
    public OrderRecordController(OrderRecordService orderRecordService) {
        this.orderRecordService = orderRecordService;
    }

    @GetMapping("orderRecords")
    public ResponseEntity<List<OrderRecordInfo>> getOrderRecordsForOrder(@RequestParam Long id) {
        return ResponseEntity.ok(orderRecordService.getOrderRecordsForOrder(id));
    }
}
