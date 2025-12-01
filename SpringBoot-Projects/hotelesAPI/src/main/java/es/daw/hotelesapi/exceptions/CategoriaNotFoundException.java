package es.daw.hotelesapi.exceptions;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String codigo) {
        super("Categoria no encontrada: " + codigo);
    }
}
