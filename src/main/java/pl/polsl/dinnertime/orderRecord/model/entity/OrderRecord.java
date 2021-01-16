package pl.polsl.dinnertime.orderRecord.model.entity;

import lombok.Data;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.user.model.entity.User;

import javax.persistence.*;

@Entity
@Data
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menuPositions;

    @Column
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;
}
