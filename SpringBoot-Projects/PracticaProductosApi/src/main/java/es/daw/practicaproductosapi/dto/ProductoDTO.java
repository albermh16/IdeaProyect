package es.daw.practicaproductosapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(min = 4, max = 4, message = "El codigo debe tener exactamente 4 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre debe tener como maximo 120 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "100.00", message = "El precio minimo es 100.00")
    private BigDecimal precio;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long codigoFabricante;

}
