package es.daw.foodexpressapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishResponseDTO {

    private Long id;
    private String name;

    private BigDecimal price;
    private BigDecimal basePrice;

    private String category;

    private RestaurantResponseDTO restaurant;
}
