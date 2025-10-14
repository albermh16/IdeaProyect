package es.daw.jakarta.cabecerasapp.service;

import es.daw.jakarta.cabecerasapp.model.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(124, "Portatil", 550, "Informatica"),
                new Producto(76, "Lavadora", 350, "Electrodomestico"),
                new Producto(12, "Teclado", 150, "Informatica"),
                new Producto(532, "Silla", 200, "Hogar")
        );
    }

    @Override
    public Optional<Producto> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Producto> findByName(String nombre) {
        return Optional.empty();
    }
}
