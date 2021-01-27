package pl.polsl.dinnertime.orderRecord.model.entity;

import lombok.Data;
import lombok.ToString;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.user.model.entity.User;

import javax.persistence.*;

@Entity
@Data
@ToString(of ="id")
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

  //  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.EAGER,optional = false,cascade = CascadeType.MERGE)
//    @JoinColumn(name="orderId",nullable = false)
    private Order order;
}
