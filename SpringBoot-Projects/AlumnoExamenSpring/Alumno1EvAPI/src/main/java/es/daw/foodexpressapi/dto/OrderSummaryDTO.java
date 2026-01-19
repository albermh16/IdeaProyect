package es.daw.foodexpressapi.dto;

import es.daw.foodexpressapi.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record OrderSummaryDTO(
        Long orderId,
        String username,
        String restaurantName,
        OrderStatus status,
        LocalDateTime orderDate,
        Long totalItems,
        BigDecimal totalAmount
) {
}
