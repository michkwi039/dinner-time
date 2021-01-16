package pl.polsl.dinnertime.user.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.polsl.dinnertime.order.model.entity.Order;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> ordersThatUserOwns;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderRecord> ordersRecords;
}
