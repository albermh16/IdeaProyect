package es.daw.comprasapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductosDTO {
    private String nombre;
    private double precio;
    private String categoria;


}
