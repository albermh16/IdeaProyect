package es.daw.hotelesmvc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDTO {
    private String codigo;
    private String nombre;
}
