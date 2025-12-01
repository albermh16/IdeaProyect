package es.daw.hotelesapi.exceptions;

public class HabitacionNotFoundException extends RuntimeException {

    public HabitacionNotFoundException(String codigo) {
        super("Habitacion no encontrado: " + codigo);
    }
}
