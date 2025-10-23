
package es.daw.productoapirest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {
    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;

    @DecimalMin(value = "100.00", message = "El precio debe ser superior a 99")
    private BigDecimal precio;

    @Size(min = 1, max = 100, message = "El codigo debe tener exactamente 4 cara")
    private String codigo;

    private Integer codigoFabricante;

}
