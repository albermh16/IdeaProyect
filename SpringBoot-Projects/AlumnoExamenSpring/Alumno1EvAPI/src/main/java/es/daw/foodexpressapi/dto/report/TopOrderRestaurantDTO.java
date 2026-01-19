package es.daw.foodexpressapi.dto.report;

import java.math.BigDecimal;

public record TopOrderRestaurantDTO(
        Long restaurantId,
        String restaurantName,
        Long totalOrders
) {
}
