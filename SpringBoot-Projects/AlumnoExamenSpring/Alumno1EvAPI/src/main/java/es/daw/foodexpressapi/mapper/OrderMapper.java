package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.entity.Restaurant;
import es.daw.foodexpressapi.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {



    public OrderResponseDTO toDTO(Order order){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());

        User user = order.getUser();
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());

        Restaurant restaurant = order.getRestaurant();
        dto.setRestaurantId(restaurant.getId());
        dto.setRestaurantName(restaurant.getName());

        return dto;
    }

}
