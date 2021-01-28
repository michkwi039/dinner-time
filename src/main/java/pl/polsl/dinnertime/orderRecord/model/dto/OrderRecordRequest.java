package pl.polsl.dinnertime.orderRecord.model.dto;

import lombok.Data;
import pl.polsl.dinnertime.order.model.entity.Order;

@Data
public class OrderRecordRequest {
    private String menuPositions;
    private Double price;
    private Long orderId;
    private Boolean coupon;

    public OrderRecordRequest(){}

    public OrderRecordRequest(String menuPositions, Double price, Long orderId, Boolean coupon) {
        this.menuPositions = menuPositions;
        this.price = price;
        this.orderId = orderId;
        this.coupon = coupon;
    }

    public OrderRecordRequest(String menuPositions, Double price, Long orderId) {
        this.menuPositions = menuPositions;
        this.price = price;
        this.orderId = orderId;
    }
}
