package es.daw.foodexpressapi.dto;

import lombok.Data;

public record CreateOrderItemDTO(
        Long dishId,
        Integer quantity
) {
}
