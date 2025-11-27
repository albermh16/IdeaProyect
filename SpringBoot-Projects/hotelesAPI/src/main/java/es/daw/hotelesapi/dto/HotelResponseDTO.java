package es.daw.hotelesapi.dto;

import es.daw.hotelesapi.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelResponseDTO {

    @NotNull(message = "El codigo es obligatorio")
    @Size(min = 7, max = 7, message = "El codigo debe tener 7 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    private boolean piscina;
    private String localidad;

    private CategoriaDTO categoria;

}
