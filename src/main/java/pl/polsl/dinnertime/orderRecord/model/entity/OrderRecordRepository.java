package pl.polsl.dinnertime.orderRecord.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;

import java.util.List;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {
    List<OrderRecord> getAllByOrder_orderId(Long id);
    List<OrderRecord> findAllByOrder(Order order);
}
