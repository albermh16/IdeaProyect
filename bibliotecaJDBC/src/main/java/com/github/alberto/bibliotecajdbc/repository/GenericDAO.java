package com.github.alberto.bibliotecajdbc.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDAO <T, ID>{

    //CREATE
    void save(T entity) throws SQLException;

    //BUSCAR POR ID
    Optional<T> findById(ID id) throws SQLException;

    //LISTAR TODO
    List<T> findAll() throws SQLException;

    //UPDATE
    void update(T entity)  throws SQLException;

    //DELETE
    void delete(T entity)  throws SQLException;
}
