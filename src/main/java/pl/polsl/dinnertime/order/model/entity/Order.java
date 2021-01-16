package pl.polsl.dinnertime.order.model.entity;

import lombok.Data;
import pl.polsl.dinnertime.user.model.entity.User;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String restaurant;

    @Column
    private String collectingPlace;

    @Column
    private ZonedDateTime orderingTime;

    @Column
    private OrderStatus orderStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordererId", nullable = false)
    private User orderingUser;

}
