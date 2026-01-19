package es.daw.foodexpressapi.dto;

import java.math.BigDecimal;

public record OrderDetailViewDTO(
        String dishName,
        String category,
        Integer quantity,
        BigDecimal price,
        BigDecimal subtotal) {
}
