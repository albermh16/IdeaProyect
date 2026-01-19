package es.daw.foodexpressapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


public record CreateOrderDTO (
    @NotNull Long userId,
    @NotNull Long restaurantId,
    @NotNull List<CreateOrderItemDTO> items
)
{
}
