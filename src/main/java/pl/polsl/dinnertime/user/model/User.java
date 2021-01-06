package pl.polsl.dinnertime.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.polsl.dinnertime.order.model.Order;
import pl.polsl.dinnertime.orderRecord.OrderRecord;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user_account")
@Data
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @Column
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> ordersThatUserOwns;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderRecord> ordersRecords;
}
