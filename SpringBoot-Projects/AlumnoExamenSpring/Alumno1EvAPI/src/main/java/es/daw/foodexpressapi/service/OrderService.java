package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.CreateOrderDTO;
import es.daw.foodexpressapi.dto.CreateOrderItemDTO;
import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.entity.*;
import es.daw.foodexpressapi.enums.OrderStatus;
import es.daw.foodexpressapi.exception.InvalidStatusException;
import es.daw.foodexpressapi.exception.RestaurantNotFoundException;
import es.daw.foodexpressapi.exception.UserNotFoundException;
import es.daw.foodexpressapi.mapper.OrderMapper;
import es.daw.foodexpressapi.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderMapper orderMapper;
    private final DishRepository dishRepository;
    private final OrderDetailRepository orderDetailRepository;

    public Page<OrderResponseDTO> filterOrders(String status, Long userId, Long restaurantId, Pageable pageable){

        OrderStatus statusEnum = null;

        if(status != null && !status.isBlank()){
            try{
                statusEnum = OrderStatus.valueOf(status.trim().toUpperCase());
            } catch (IllegalArgumentException ex){
                throw new InvalidStatusException("Invalid status:" + status);
            }

        }

        if(userId != null  && !userRepository.existsById(userId)){
            throw new UserNotFoundException(userId);
        }

        if(restaurantId != null && !restaurantRepository.existsById(restaurantId)){
            throw new RestaurantNotFoundException(restaurantId);
        }

        Page<Order> orders = orderRepository.findByFilters(statusEnum, userId, restaurantId, pageable);

        return orders.map(orderMapper::toDTO);
    }


    public Page<OrderSummaryDTO> getOrderSumaries(Pageable pageable){
        return orderRepository.getAllOrderSummaries(pageable);
    }

    @Transactional
    public OrderSummaryDTO createOrder(CreateOrderDTO dto){
        if(dto.items().isEmpty()) throw new RuntimeException("No existen platos en la orden");

        User user = userRepository.findById(dto.userId())
                .orElseThrow( () -> new UserNotFoundException(dto.userId()));

        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow( () -> new RestaurantNotFoundException(dto.restaurantId()));


        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setStatus(OrderStatus.CREADO);
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);

        long totalItems = 0L;
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(CreateOrderItemDTO item : dto.items()){
            Dish dish = dishRepository.findById(item.dishId())
                    .orElseThrow( () -> new EntityNotFoundException("Dish not found: " + item.dishId()));

            if(dish.getRestaurant() == null || dish.getRestaurant().getId() == null || !dish.getRestaurant().getId().equals(dto.restaurantId())){
                throw new IllegalArgumentException("Dish " + dish.getId() + "no corresponde con el restaurante " + dto.restaurantId());
            }

            BigDecimal unitPrice = dish.getPrice();
            if(unitPrice == null){
                throw new IllegalArgumentException("Dish price is null for dishId");
            }

            int qty = item.quantity();

            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(qty));

            OrderDetails details = new OrderDetails();
            details.setOrder(order);
            details.setDish(dish);
            details.setQuantity(qty);
            details.setSubtotal(subtotal);

            details.setOrderDetailId(new OrderDetailId(order.getId(), dish.getId()));

            orderDetailRepository.save(details);

            totalAmount = totalAmount.add(subtotal);
            totalItems += qty;


        }

        return new OrderSummaryDTO(
                order.getId(),
                user.getUsername(),
                restaurant.getName(),
                OrderStatus.CREADO,
                order.getOrderDate(),
                totalItems,
                totalAmount
        );








    }
}
