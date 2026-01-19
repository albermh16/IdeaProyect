package es.daw.foodexpressmvc.dto;

import lombok.Data;

@Data
public class OrderFilterDTO {
    private String status;
    private Long userId;
    private Long restaurantId;
}
