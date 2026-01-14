package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.enums.OrderStatus;
import es.daw.foodexpressapi.exception.InvalidStatusException;
import es.daw.foodexpressapi.exception.RestaurantNotFoundException;
import es.daw.foodexpressapi.exception.UserNotFoundException;
import es.daw.foodexpressapi.mapper.OrderMapper;
import es.daw.foodexpressapi.repository.OrderRepository;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import es.daw.foodexpressapi.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponseDTO> buscar(String status, Long userId, Long restaurantId){

        List<Order> orders;

        //Validar STATUS
        if(status != null && !status.isBlank()){
            try{
                OrderStatus enumStatus = OrderStatus.valueOf(status.toUpperCase());
                status = enumStatus.name();
            } catch (IllegalArgumentException e){
                throw new InvalidStatusException("Invalid status: "+ status);
            }
        } else{
            status = null;
        }

        //Validar existencia userId / restaurantId

        if(userId != null && !userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }

        if(restaurantId != null && !restaurantRepository.existsById(restaurantId)){
            throw new RestaurantNotFoundException(restaurantId);
        }


        if(status != null && userId != null && restaurantId != null){
           orders = orderRepository.findByStatusAndRestaurant_IdAndUser_Id(status, restaurantId, userId);

        } else if( status != null && userId != null ){

            orders = orderRepository.findByStatusAndUser_Id(status, userId);

        } else if( status != null  && restaurantId != null){

            orders = orderRepository.findByStatusAndRestaurant_Id(status, restaurantId);

        } else if( restaurantId != null && userId != null){

            orders = orderRepository.findByRestaurant_IdAndUser_Id(restaurantId, userId);

        } else if ( status != null) {

            orders = orderRepository.findByStatus(status);

        } else if ( userId != null) {

            orders = orderRepository.findByUser_Id(userId);

        } else if ( restaurantId != null) {

            orders = orderRepository.findByRestaurant_Id(restaurantId);

        } else {
            orders = orderRepository.findAll();
        }


        return orders.stream().map(orderMapper::toDTO).toList();
    }
}
