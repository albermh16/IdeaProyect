package es.daw.practicaproductosapi.exception;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(String message){
        super(message);
    }
}
