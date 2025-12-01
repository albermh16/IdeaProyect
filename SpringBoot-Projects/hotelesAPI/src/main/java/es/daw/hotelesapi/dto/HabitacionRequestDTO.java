package es.daw.hotelesapi.dto;

import es.daw.hotelesapi.entity.Hotel;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HabitacionRequestDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @NotNull

    private String codigo;

    @NotNull(message = "Debes elegir una opcion")
    @Min(value = 10, message = "El tamaño de la habitacion debe ser superior a 10m cuadrados")
    private Integer tamano;

    private boolean doble;


    @Min(value = 1, message = "El precio por noche debe ser mayor a 0")
    private BigDecimal precioNoche;

    private boolean incluyeDesayuno;




}

