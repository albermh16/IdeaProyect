package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.RestaurantRequestDTO;
import es.daw.foodexpressapi.dto.RestaurantResponseDTO;
import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantResponseDTO toDTO(Restaurant restaurant){
        return RestaurantResponseDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .build();
    }

    public Restaurant toEntity(RestaurantRequestDTO dto){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        return restaurant;
    }
}
