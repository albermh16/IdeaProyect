package es.daw.hotelesapi.dto;

import es.daw.hotelesapi.entity.Hotel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HabitacionRequestDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(min = 9, max = 9, message = "El codigo debe tener 9 caracteres")
    private String codigo;

    @NotNull(message = "Debes elegir una opcion")
    private Integer tamano;

    private boolean doble;

    @DecimalMin(value = "30.0", message = "El precio debe ser superior a 29")
    private BigDecimal precioNoche;

    private boolean incluyeDesayuno;


    private Hotel hotel;


}

