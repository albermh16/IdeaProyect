package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.entity.Dish;
import es.daw.foodexpressapi.enums.DishCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final RestaurantMapper restaurantMapper;

    public DishResponseDTO toDTO(Dish dish) {
        if (dish == null) return null;

        var basePrice = dish.getPrice();

        DishResponseDTO dto = new DishResponseDTO();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());
        dto.setCategory(dish.getCategory().getLabel());
        dto.setBasePrice(basePrice);
        dto.setPrice(dish.getCategory().applyPlus(basePrice));
        dto.setRestaurant(
                restaurantMapper.toDTO(dish.getRestaurant())
        );

        return dto;
    }

}


