package es.daw.foodexpressapi.dto.report;

public record TopDishesDTO(
        Long dishId,
        String dishName,
        Long totalUnits
        ){
}
