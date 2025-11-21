package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.DishesDTO;
import es.daw.foodexpressapi.entity.Dishes;
import es.daw.foodexpressapi.repository.DishesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishesService {

    private final DishesRepository dishesRepository;

    public List<DishesDTO> getAllDishes(){
        return dishesRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public DishesDTO toDto(Dishes dishes){
        return DishesDTO.builder()
                .name(dishes.getName())
                .price(dishes.getPrice())
                .category(dishes.getCategory())
                .restaurantName(dishes.getRestaurant().getName())
                .build();
    }
}
