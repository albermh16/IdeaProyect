package es.daw.hotelesapi.exceptions;


public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String codigo) {
        super("Hotel no encontrado: " + codigo);
    }
}
