package es.daw.hotelesmvc.exception;

public class ConnectApiRestException extends RuntimeException{
    public ConnectApiRestException(String message){
        super(message);
    }
}
