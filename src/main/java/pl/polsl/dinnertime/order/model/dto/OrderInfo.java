package pl.polsl.dinnertime.order.model.dto;

import lombok.Data;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.order.model.entity.OrderStatus;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.user.model.dto.SimpleUser;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OrderInfo {

    private Long id;

    private String restaurant;

    private String collectingPlace;

    private ZonedDateTime orderingTime;

    private OrderStatus orderStatus;

    private SimpleUser orderingUser;

    private List<OrderRecord> orderRecord;

    public OrderInfo(Order order) {
        this.id = order.getId();
        this.restaurant = order.getRestaurant();
        this.collectingPlace = order.getCollectingPlace();
        this.orderingTime = order.getOrderingTime();
        this.orderStatus = order.getOrderStatus();
        this.orderingUser = new SimpleUser(order.getOrderingUser());
        this.orderRecord = order.getOrderRecord();
    }
}
