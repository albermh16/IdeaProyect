package es.daw.foodexpressapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishResponseDTO {

    private String name;

    private BigDecimal price;

    private String category;

    private RestaurantResponseDTO restaurant;
}
