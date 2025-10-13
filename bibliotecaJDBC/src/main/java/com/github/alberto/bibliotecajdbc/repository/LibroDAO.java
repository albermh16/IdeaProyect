package com.github.alberto.bibliotecajdbc.repository;

import com.github.alberto.bibliotecajdbc.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO implements GenericDAO<Libro, Long>{

    private Connection conn;

    public LibroDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }


    @Override
    public void save(Libro entity) throws SQLException {
        String sql = "INSERT INTO book (id, title, author_id, publication_date) VALUES (?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getTitle());
            ps.setLong(3, entity.getAuthor_id());
            ps.setDate(4, (Date) entity.getPublication_date());

            ps.executeUpdate();


        }catch (SQLException e){
            throw new SQLException("Error al insertar libro");
        }

    }

    @Override
    public Optional<Libro> findById(Long id) throws SQLException {

        String sql = "SELECT * FROM book WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, (Long) id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getDate("publication_date")
                );

                return Optional.of(libro);
            }

        }
        return Optional.empty();
    }


    @Override
    public List findAll() throws SQLException {

        List libros = new ArrayList();
        String sql = "SELECT * FROM book";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                libros.add(
                        new Libro(
                                rs.getLong("id"),
                                rs.getString("title"),
                                rs.getLong("author_id"),
                                rs.getDate("publication_date")
                        )
                );
            }
        }
        return libros;
    }

    public List<Libro> findByTitleOrAuthor(String filter) throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = """
            SELECT b.*
            FROM book b
            JOIN author a ON b.author_id = a.id
            WHERE LOWER(b.title) LIKE ? OR LOWER(a.name) LIKE ?
        """;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "%" + filter.toLowerCase() + "%");
            ps.setString(2, "%" + filter.toLowerCase() + "%");
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    libros.add(new Libro(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getLong("author_id"),
                            rs.getDate("publication_date")
                    ));
                }
                return libros;
        }
    }


    @Override
    public void update(Libro entity) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ps.executeUpdate();

        }

    }


}
