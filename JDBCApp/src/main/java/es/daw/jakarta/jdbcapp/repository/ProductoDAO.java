package es.daw.jakarta.jdbcapp.repository;

import es.daw.jakarta.jdbcapp.model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO extends GenericDAO<Producto, Integer>{
    List<Producto> findAllWithFabricante() throws SQLException; // para la mejora
    int nextCodigo() throws SQLException;
}
