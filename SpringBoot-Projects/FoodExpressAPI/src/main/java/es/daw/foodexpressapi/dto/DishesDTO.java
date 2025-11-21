package es.daw.foodexpressapi.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishesDTO {
    private String name;
    private BigDecimal price;
    private String category;
    private String restaurantName;
}
