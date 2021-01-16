package pl.polsl.dinnertime.order.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.dinnertime.order.model.entity.Order;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> getOrdersByOrderingTimeBefore(ZonedDateTime time);
}
