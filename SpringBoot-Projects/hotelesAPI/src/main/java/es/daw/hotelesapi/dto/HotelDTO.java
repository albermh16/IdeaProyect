package es.daw.hotelesapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HotelDTO {

    @NotNull(message = "El codigo es obligatorio")
    @Size(min = 7, max = 7, message = "El codigo debe tener 7 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    private boolean piscina;

    @NotBlank(message = "Localidad obligatoria")
    private String localidad;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoria_id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String categoria_nombre;

}
