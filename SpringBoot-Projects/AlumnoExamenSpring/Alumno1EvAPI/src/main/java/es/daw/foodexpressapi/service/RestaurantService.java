package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.RestaurantRequestDTO;
import es.daw.foodexpressapi.dto.RestaurantResponseDTO;
import es.daw.foodexpressapi.entity.Restaurant;
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
    private final RestaurantMapper restaurantMapper;

    public List<RestaurantResponseDTO> getAllRestaurants(){
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toDTO)
                .toList();

    }

    public Optional<RestaurantResponseDTO> create(RestaurantRequestDTO restaurantDTO){
        Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
        Restaurant saved = restaurantRepository.save(restaurant);
        return Optional.of(restaurantMapper.toDTO(saved));
    }


    public boolean delete(Long id){
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El restaurante no existe con código "+id));

        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhone(restaurantDTO.getPhone());

        Restaurant updated= restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(updated);

    }





}
