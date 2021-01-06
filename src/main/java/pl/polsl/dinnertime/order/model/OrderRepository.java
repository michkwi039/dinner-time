package pl.polsl.dinnertime.order.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> getOrdersByOrderingTimeBefore(ZonedDateTime time);
}
