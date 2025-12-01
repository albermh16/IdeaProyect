package es.daw.hotelesapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HotelRequestDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(min = 7, max = 7, message = "El codigo debe tener 7 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    private boolean piscina;
    private String localidad;

    @NotBlank
    private String codigoCategoria;

}
