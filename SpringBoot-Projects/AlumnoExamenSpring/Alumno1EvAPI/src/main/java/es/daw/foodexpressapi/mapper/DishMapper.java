package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final RestaurantMapper restaurantMapper;

    public DishResponseDTO toDTO(Dish dish) {
        if (dish == null) return null;

        DishResponseDTO dto = new DishResponseDTO();
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());
        dto.setCategory(dish.getCategory());

        dto.setRestaurant(
                restaurantMapper.toDTO(dish.getRestaurant())
        );

        return dto;
    }

}


