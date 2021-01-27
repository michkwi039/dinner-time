package pl.polsl.dinnertime.order.model.dto;

import lombok.Data;
import pl.polsl.dinnertime.orderRecord.model.entity.OrderRecord;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class NewOrderRequest {
    private String restaurant;
    private ZonedDateTime time;
    private String collectingPlace;
    private String menuPositions;
    private Double price;

    public NewOrderRequest() {
    }

    public NewOrderRequest(String restaurant, ZonedDateTime time, String collectingPlace,String menuPositions, Double price) {
        this.restaurant = restaurant;
        this.time = time;
        this.collectingPlace = collectingPlace;
    }
}
