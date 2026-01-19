package es.daw.foodexpressmvc.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailViewDTO {
    private String dishName;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}
