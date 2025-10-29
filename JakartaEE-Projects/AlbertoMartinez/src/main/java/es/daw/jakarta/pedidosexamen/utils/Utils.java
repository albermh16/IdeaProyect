package es.daw.jakarta.pedidosexamen.utils;

import es.daw.jakarta.pedidosexamen.model.Cliente;

import java.util.List;

public class Utils {
    public static String obtenerNombreCliente(List<Cliente> clientes, Long clienteId) {
        return clientes.stream()
                .filter(c -> c.getId().equals(clienteId))
                .findFirst()
                .map(Cliente::getNombre)
                .orElse("Desconocido");
    }
}
