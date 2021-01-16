package pl.polsl.dinnertime.order.service;

import org.springframework.stereotype.Service;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.order.model.entity.OrderRepository;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getCurrentOrders() {
        return orderRepository.getOrdersByOrderingTimeBefore(ZonedDateTime.now())
                .orElseThrow(NullPointerException::new);
    }
}
