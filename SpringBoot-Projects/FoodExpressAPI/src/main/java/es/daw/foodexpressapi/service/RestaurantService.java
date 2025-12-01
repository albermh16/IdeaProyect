package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.entity.Restaurant;
import es.daw.foodexpressapi.mapper.RestaurantManualMapper;
import es.daw.foodexpressapi.mapper.RestaurantMapper;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ResourcePatternResolver resourcePatternResolver;
    //private final RestaurantMapper restaurantMapper;
    private final RestaurantManualMapper restaurantManualMapper;

    public List<RestaurantDTO> getAllRestaurants(){
        return restaurantRepository.findAll().stream()
                //.map(this::toDTO)
                //.map(restaurantMapper::toDTO)
                .map(restaurantManualMapper::toDTO)
                .toList();

    }

    public Optional<RestaurantDTO> create(RestaurantDTO restaurantDTO){
        Restaurant restaurant = restaurantManualMapper.toEntity(restaurantDTO);
        Restaurant saved = restaurantRepository.save(restaurant);
        return Optional.of(restaurantManualMapper.toDTO(saved));
        //return Optional.of(restaurantMapper.toDTO(saved));
    }


    /**
     *
     * @param id
     * @return
     */
    public boolean delete(Long id){
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public RestaurantDTO update(Long id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El restaurante no existe con código "+id));

        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhone(restaurantDTO.getPhone());

        Restaurant updated= restaurantRepository.save(restaurant);
        return restaurantManualMapper.toDTO(updated);

    }





}
