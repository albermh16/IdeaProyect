package es.daw.hotelesmvc.dto;


import lombok.Data;

@Data
public class HotelDTO {

    private String codigo;
    private String nombre;
    private String descripcion;
    private boolean piscina;
    private String localidad;
    private CategoriaDTO categoria;

}
