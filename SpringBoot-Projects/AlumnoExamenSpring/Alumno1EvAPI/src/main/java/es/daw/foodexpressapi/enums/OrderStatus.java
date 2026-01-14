package es.daw.foodexpressapi.enums;

public enum OrderStatus {
    CREADO,
    PREPARANDO,
    EN_CAMINO,
    ENTREGADO,
    CANCELADO;

    public static boolean isValid(String value) {
        if (value == null) return true;
        try {
            OrderStatus.valueOf(value);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}


