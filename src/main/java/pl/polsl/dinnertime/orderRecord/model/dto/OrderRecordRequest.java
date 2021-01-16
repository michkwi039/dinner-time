package pl.polsl.dinnertime.orderRecord.model.dto;

import lombok.Data;
import pl.polsl.dinnertime.order.model.entity.Order;

@Data
public class OrderRecordRequest {
    private String menuPositions;
    private Double price;
    private Long orderId;

    public OrderRecordRequest(){}

    public OrderRecordRequest(String menuPositions, Double price, Long orderId) {
        this.menuPositions = menuPositions;
        this.price = price;
        this.orderId = orderId;
    }
}
