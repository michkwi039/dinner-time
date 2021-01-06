package pl.polsl.dinnertime.orderRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecordRepository extends JpaRepository<OrderRecord, Long> {
}
