package es.daw.foodexpressmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DishesDTO {
    private String name;
    private BigDecimal price;
    private String category;
    private String restaurantName;
}
