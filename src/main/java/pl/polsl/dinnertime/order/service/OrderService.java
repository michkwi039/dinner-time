package pl.polsl.dinnertime.order.service;

import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.order.model.dto.NewOrderRequest;
import pl.polsl.dinnertime.order.model.dto.OrderInfo;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.order.model.entity.OrderRepository;
import pl.polsl.dinnertime.order.model.entity.OrderStatus;
import pl.polsl.dinnertime.user.model.entity.User;
import pl.polsl.dinnertime.user.model.entity.UserRepository;
import pl.polsl.dinnertime.user.service.UserService;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserService userService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<OrderInfo> getCurrentOrders() {
        return orderRepository.getOrderByOrderingTimeAfter(ZonedDateTime.now())
                .stream()
                .map(OrderInfo::new)
                .sorted(Comparator.comparing(OrderInfo::getOrderingTime))
                .collect(Collectors.toList());
    }

    public void createOrder(NewOrderRequest newOrderRequest) {
        Order order = new Order();
        order.setOrderingTime(newOrderRequest.getTime());
        order.setRestaurant(newOrderRequest.getRestaurant());
        order.setCollectingPlace(newOrderRequest.getCollectingPlace());
        order.setOrderStatus(OrderStatus.OPEN);
        User user = userService.authenticateUser();
        order.setOrderingUser(user);
        orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOne(orderId);
    }
}
