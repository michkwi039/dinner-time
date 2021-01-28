package pl.polsl.dinnertime.orderRecord.service;

import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.exceptions.UserNotFoundException;
import pl.polsl.dinnertime.order.service.OrderService;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordInfo;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecordRepository;
import pl.polsl.dinnertime.user.model.entity.User;
import pl.polsl.dinnertime.user.model.entity.UserRepository;
import pl.polsl.dinnertime.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderRecordService {
    private final OrderRecordRepository orderRecordRepository;
    private final UserService userService;
    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderRecordService(OrderRecordRepository orderRecordRepository, UserService userService, OrderService orderService, UserRepository userRepository) {
        this.orderRecordRepository = orderRecordRepository;
        this.userService = userService;
        this.orderService = orderService;
        this.userRepository=userRepository;
    }

    public OrderRecord createOrderRecord(OrderRecordRequest orderRecordRequest){
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setMenuPositions(orderRecordRequest.getMenuPositions());
        orderRecord.setPrice(orderRecordRequest.getPrice());
        orderRecord.setUser(userService.authenticateUser());
//        orderRecord.setUser(userRepository.getUserByUsername("samolot")
//                .orElseThrow(() -> new UserNotFoundException()));
        orderRecord.setOrder(orderService.getOrderById(orderRecordRequest.getOrderId()));
        return orderRecordRepository.save(orderRecord);
    }

    public List<OrderRecordInfo> getOrderRecordsForOrder(Long id) {
        return orderRecordRepository.getAllByOrder_orderId(id)
                .stream()
                .map(OrderRecordInfo::new)
                .collect(Collectors.toList());
    }
}
