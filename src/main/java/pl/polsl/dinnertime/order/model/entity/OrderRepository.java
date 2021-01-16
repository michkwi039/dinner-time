package pl.polsl.dinnertime.order.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrderByOrderingTimeAfter(ZonedDateTime time);
}
