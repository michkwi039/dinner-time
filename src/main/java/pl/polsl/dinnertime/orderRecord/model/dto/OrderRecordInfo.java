package pl.polsl.dinnertime.orderRecord.model.dto;

import lombok.Data;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;
import pl.polsl.dinnertime.user.model.dto.SimpleUser;

@Data
public class OrderRecordInfo {

    private Long id;

    private String menuPositions;

    private Double price;

    private SimpleUser user;

    public OrderRecordInfo(OrderRecord orderRecord) {
        this.id = orderRecord.getId();
        this.menuPositions = orderRecord.getMenuPositions();
        this.user = new SimpleUser(orderRecord.getUser());
        this.price = orderRecord.getPrice();
    }
}
