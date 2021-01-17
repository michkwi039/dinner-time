package pl.polsl.dinnertime.order.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.user.model.entity.User;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "order_table")
@Data
@EqualsAndHashCode(of = "id")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Users_Orders",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> users;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ordererId", nullable = false)
    private User orderingUser;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderRecordId")
    private List<OrderRecord> orderRecord;

    public void addUser(User user) {
        Set<User> users = getUsers();
        users.add(user);
        setUsers(users);
    }

}
