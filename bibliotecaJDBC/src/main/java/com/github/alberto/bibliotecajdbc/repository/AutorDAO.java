package com.github.alberto.bibliotecajdbc.repository;

import com.github.alberto.bibliotecajdbc.model.Autor;
import com.github.alberto.bibliotecajdbc.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAO implements GenericDAO<Autor, Long>{
    private Connection conn;

    public AutorDAO() throws SQLException {
        conn =DBConnection.getConnection();
    }

    @Override
    public void save(Autor entity) throws SQLException {
        String sql = "INSERT INTO Author (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getNombre());
            ps.executeUpdate();
        }

    }

    @Override
    public Optional<Autor> findById(Long aLong) throws SQLException {

        String sql = "select * from autor where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, aLong);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Autor autor = new Autor(
                    rs.getLong("id"),
                    rs.getString("name")
            );
            return Optional.of(autor);
        }
        return Optional.empty();
    }



    @Override
    public List<Autor> findAll() throws SQLException {
        List<Autor> autores = new ArrayList();
        String sql = "SELECT * FROM author";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                autores.add(
                        new Autor(
                                rs.getLong("id"),
                                rs.getString("name")
                        )
                );
            }
            return autores;
        }
    }

    @Override
    public void update(Autor entity) throws SQLException {

    }

    @Override
    public void delete(Long aLong) throws SQLException {
        String sql = "delete from author where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, aLong);
            ps.executeUpdate();
        }

    }




}
