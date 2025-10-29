package es.daw.jakarta.pedidosexamen.repository;

import es.daw.jakarta.pedidosexamen.model.Cliente;
import es.daw.jakarta.pedidosexamen.model.Pedido;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static es.daw.jakarta.pedidosexamen.repository.DBConnection.getConnection;

public class PedidoDAO implements GenericDAO<Pedido, Long>{

    private Connection conn;

    public PedidoDAO() throws SQLException {
        conn = getConnection();
    }
    @Override
    public void save(Pedido entity) throws SQLException {

    }

    @Override
    public Optional<Pedido> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Pedido> findAll() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            // BigInteger id_cliente, LocalDateTime fechaPedido, double total, EstadoPedido estado)
            while(rs.next()){
                pedidos.add(
                        new Pedido(
                                rs.getLong("cliente_id"),
                                rs.getTimestamp("fecha_pedido").toLocalDateTime(),
                                rs.getBigDecimal("total"),
                                Pedido.EstadoPedido.valueOf(rs.getString("estado"))

                        )
                );
            }
        }
        return pedidos;
    }

    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        String sql = "SELECT * FROM Pedido WHERE cliente_id = ?";
        List<Pedido> pedidos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pedidos.add(
                        new Pedido(
                                rs.getLong("cliente_id"),
                                rs.getTimestamp("fecha_pedido").toLocalDateTime(),
                                rs.getBigDecimal("total"),
                                Pedido.EstadoPedido.valueOf(rs.getString("estado"))
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    @Override
    public void update(Pedido entity) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
