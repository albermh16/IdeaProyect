package es.daw.foodexpressapi.dto;

import lombok.Data;

@Data
public class RestaurantRequestDTO {
    private String name;

    private String address;

    private String phone;
}
