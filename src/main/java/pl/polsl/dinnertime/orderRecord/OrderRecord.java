package pl.polsl.dinnertime.orderRecord;

import lombok.Data;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
}
