package es.daw.foodexpressapi.dto;

import lombok.Data;

import java.math.BigDecimal;


public record OrderDetailsDTO (
        Long orderId,
        String dishName,
        Integer quantity,
        BigDecimal subtotal
){


}
