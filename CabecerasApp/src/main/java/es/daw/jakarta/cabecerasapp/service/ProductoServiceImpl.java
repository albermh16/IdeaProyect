package es.daw.jakarta.cabecerasapp.service;

import es.daw.jakarta.cabecerasapp.model.Producto;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return List.of(
                new Producto(1, "Portatil", 550, "Informatica"),
                new Producto(2, "Lavadora", 350, "Electrodomestico"),
                new Producto(3, "Teclado", 150, "Informatica"),
                new Producto(4, "Silla", 200, "Hogar")
        );
    }
}
