package es.daw.jakarta.jdbcapp.repository;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FabricanteDAO implements GenericDAO<Fabricante, Integer> {

    private Connection conn;

   public FabricanteDAO(Connection conn) throws SQLException {
       conn= DBConnection.getConnection();
   }

    @Override
    public void save(Fabricante entity) throws SQLException {
        String sql = "INSERT INTO producto (codigo,nombre, precio,codigo_fabricante) VALUES (?,?,?,?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, entity.getCodigo());
            ps.setString(2, entity.getNombre());
        }catch(SQLException e){
            if("23505".equals(e.getSQLState())){
                throw new SQLException("El fabricante que intenta insertar ya existe");
            }
        }



    }

    @Override
    public Optional<Fabricante> findById(Integer id) throws SQLException {

       String sql = "SELECT * FROM producto  WHERE codigo = ?";

       try(PreparedStatement ps = conn.prepareStatement(sql)){
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               Fabricante fabricante = new Fabricante(
                   rs.getInt("codigo"),
                   rs.getString("nombre")
               );
               return Optional.of(fabricante);
           }

       }
        return Optional.empty();
    }

    @Override
    public List<Fabricante> findAll() throws SQLException {

        List<Fabricante> fabricante = new ArrayList<>();
        String sql = "SELECT * FROM fabricante";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                fabricante.add(
                        new Fabricante(
                            rs.getInt("codigo"),
                            rs.getString("nombre")
                        )
                );
            }
        }
        return fabricante;
    }

    @Override
    public void update(Fabricante entity) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
