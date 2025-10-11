package com.github.alberto.bibliotecajdbc.repository;

import com.github.alberto.bibliotecajdbc.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO implements GenericDAO{

    private Connection conn;

    public LibroDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void save(Object entity) throws SQLException {


    }

    @Override
    public Optional findById(Object o) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List findAll() throws SQLException {

        List libros = new ArrayList();
        String sql = """
            SELECT b.title, a.name AS author_name, b.publication_date
            FROM Book b
            LEFT JOIN Author a ON b.author_id = a.id
        """;

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                libros.add(
                        new Libro(
                                rs.getString("title"),
                                rs.getString("author_name"),
                                rs.getDate("publication_date")
                        )
                );
            }
        }
        return libros;
    }

    @Override
    public void update(Object entity) throws SQLException {

    }

    @Override
    public void delete(Object entity) throws SQLException {

    }
}
