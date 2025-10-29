package es.daw.jakarta.pedidosexamen.controller;

import es.daw.jakarta.pedidosexamen.model.Cliente;
import es.daw.jakarta.pedidosexamen.repository.ClienteDAO;
import es.daw.jakarta.pedidosexamen.repository.GenericDAO;
import es.daw.jakarta.pedidosexamen.repository.PedidoDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/clientes/eliminar")
public class BorrarClientes extends HttpServlet {
    private static final Logger log =  Logger.getLogger(ListarPedidosServlet.class.getName());

    PedidoDAO pedidoDAO = null;
    private GenericDAO<Cliente,Long> clienteDAO = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            pedidoDAO = new PedidoDAO();
            clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            log.severe(e.getMessage());
            throw new ServletException("Error inicializando DAO",e);
        }


    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id != null && !id.isEmpty()){

            Long idCliente = Long.parseLong(id);
            try {

                clienteDAO.delete(idCliente);

                res.sendRedirect(req.getContextPath() + "/clientes/listar" );

            } catch (SQLException e) {
                log.severe("Error al eliminar cliente: " + e.getMessage());

                // Detecta error de integridad referencial
                if (e.getMessage().toLowerCase().contains("referential integrity")) {
                    req.setAttribute("mensajeError", "Error al recuperar los clientes: " + e.getMessage());
                } else {
                    req.setAttribute("mensajeError", "Error al eliminar el cliente: " + e.getMessage());
                }

                // Envía a una página de error (o la misma de clientes)
                getServletContext()
                        .getRequestDispatcher("/error.jsp")
                        .forward(req, res);
            }


        }




    }
}
