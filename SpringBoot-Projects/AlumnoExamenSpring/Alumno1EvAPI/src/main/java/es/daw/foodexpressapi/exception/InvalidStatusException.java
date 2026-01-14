package es.daw.foodexpressapi.exception;

import es.daw.foodexpressapi.enums.OrderStatus;

import java.util.Arrays;

public class InvalidStatusException extends RuntimeException {

    public InvalidStatusException(String status) {
        super("Estado de pedido no válido: " + status +
              ". Valores permitidos: " + Arrays.toString(OrderStatus.values()));
    }
}

