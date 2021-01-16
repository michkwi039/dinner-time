package pl.polsl.dinnertime.orderRecord.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {
}
