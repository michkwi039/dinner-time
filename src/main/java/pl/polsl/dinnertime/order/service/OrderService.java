package pl.polsl.dinnertime.order.service;

import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.order.model.entity.OrderRepository;
import pl.polsl.dinnertime.order.model.entity.OrderStatus;
import pl.polsl.dinnertime.orderRecord.model.dto.OrderRecordRequest;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecordRepository;
import pl.polsl.dinnertime.user.model.entity.User;
import pl.polsl.dinnertime.user.model.entity.UserRepository;
import pl.polsl.dinnertime.user.service.UserService;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderRecordRepository orderRecordRepository;

    public OrderService(OrderRepository orderRepository, UserService userService, UserRepository userRepository,OrderRecordRepository orderRecordRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userRepository=userRepository;
        this.orderRecordRepository=orderRecordRepository;
    }

    public List<OrderInfo> getCurrentOrders() {
//        orderRepository.findAll();
        List<Order> orderInfos=orderRepository.getOrderByOrderingTimeAfter(ZonedDateTime.now());

        for (Order o:
             orderInfos) {
            o.setOrderRecord(getOrderRecordsForOrder(o));
        }
        return orderInfos.stream()
                .map(OrderInfo::new)
                .sorted(Comparator.comparing(OrderInfo::getOrderingTime))
                .collect(Collectors.toList());
    }
    public Set<OrderRecord> getOrderRecordsForOrder(Order order){
        order.setOrderRecord(new HashSet<>(orderRecordRepository.findAllByOrder(order)));
        return order.getOrderRecord();
    }
    @org.springframework.transaction.annotation.Transactional
    public Order createOrder(NewOrderRequest newOrderRequest) {
        Order order = new Order();
        order.setOrderingTime(newOrderRequest.getTime());
        order.setRestaurant(newOrderRequest.getRestaurant());
        order.setCollectingPlace(newOrderRequest.getCollectingPlace());
        order.setOrderStatus(OrderStatus.OPEN);
        order.setCoupon(newOrderRequest.getCoupon());
        User user = userService.authenticateUser();
//        User user=userRepository.getUserByUsername("admin")
//                .orElseThrow(() -> new UserNotFoundException());
        order.setOrderingUser(user);
        order=orderRepository.save(order);
        OrderRecordRequest orderRecordRequest= new OrderRecordRequest(newOrderRequest.getMenuPositions(),newOrderRequest.getPrice(),order.getOrderId());
//        order=orderRepository.save(order);
//        OrderRecordRequest orderRecordRequest= new OrderRecordRequest(newOrderRequest.getMenuPositions(),newOrderRequest.getPrice(),order.getId());
//        orderRecordRequest.setOrderId(order.getId());
        OrderRecord orderRecord=createOrderRecord(orderRecordRequest);
//        order.addOrderRecord(orderRecord);
//        orderRecord.setOrder(order);
//        orderRecordRepository.save(orderRecord);
        order=orderRepository.getOne(order.getOrderId());
        order.setOrderRecord(new HashSet<>(orderRecordRepository.findAllByOrder(order)));
        return order;
    }
    public OrderRecord createOrderRecord(OrderRecordRequest orderRecordRequest){
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setMenuPositions(orderRecordRequest.getMenuPositions());
        orderRecord.setPrice(orderRecordRequest.getPrice());
        orderRecord.setUser(userService.authenticateUser());
//        orderRecord.setUser(userRepository.getUserByUsername("admin")
//                .orElseThrow(() -> new UserNotFoundException()));
        orderRecord.setOrder(getOrderById(orderRecordRequest.getOrderId()));
        return orderRecordRepository.save(orderRecord);
    }
    public void updateOrder(Order order){
        orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOne(orderId);
    }

    public void joinToOrder(Long orderId) {
        Order order = orderRepository.getOne(orderId);
        User user = userService.authenticateUser();
        order.addUser(user);
        orderRepository.save(order);
    }
    public Boolean takeOverOrder(Long orderID){
        Order order= orderRepository.getOne(orderID);
        if(!order.getCoupon()){
            User owner=order.getOrderingUser();
            User user = userService.authenticateUser();
//            User user=userRepository.getUserByUsername("samolot")
//                    .orElseThrow(() -> new UserNotFoundException());
            order.addUser(owner);
            order.setCoupon(true);
            order.setOrderingUser(user);
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
