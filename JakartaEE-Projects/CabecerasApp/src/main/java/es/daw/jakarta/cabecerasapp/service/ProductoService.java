package es.daw.jakarta.cabecerasapp.service;

import es.daw.jakarta.cabecerasapp.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> listar();

    // ifPresent
    public Optional<Producto> findById(int id);
    // Si no encuentro producto devuelve nulo... NPE
    public Optional<Producto> findByName(String nombre);

}
