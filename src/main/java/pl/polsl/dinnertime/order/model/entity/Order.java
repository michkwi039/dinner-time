package pl.polsl.dinnertime.order.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.user.model.entity.User;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "order_table")
@Data
@ToString(of ="orderId")
@EqualsAndHashCode(of = "orderId")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private String restaurant;

    @Column
    private String collectingPlace;

    @Column
    private Boolean coupon;

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
//    @OneToMany(targetEntity=User.class, mappedBy="userRole",cascade=CascadeType.ALL, fetch = FetchType.LAZY)

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderRecord> orderRecord;

    public void addUser(User user) {
        Set<User> users = getUsers();
        users.add(user);
        setUsers(users);
    }
//    public void addOrderRecord(OrderRecord orderRecord){
//        if(this.orderRecord!=null) {
//            this.orderRecord.add(orderRecord);
//        }else{
//            this.orderRecord=new ArrayList<>();
//            this.orderRecord.add(orderRecord);
//        }
//    }

}
