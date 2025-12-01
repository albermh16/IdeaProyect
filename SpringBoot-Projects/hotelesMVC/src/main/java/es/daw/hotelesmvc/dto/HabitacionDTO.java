package es.daw.hotelesmvc.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HabitacionDTO {
    private String codigo;
    private String nombre;
    private Integer tamano;
    private boolean doble;
    private BigDecimal precioNoche;
    private boolean incluyeDesayuno;
    private boolean ocupada;
}
