package es.daw.jakarta.pedidosexamen.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import es.daw.jakarta.pedidosexamen.model.Cliente;
import es.daw.jakarta.pedidosexamen.model.Pedido;
import es.daw.jakarta.pedidosexamen.repository.ClienteDAO;
import es.daw.jakarta.pedidosexamen.repository.GenericDAO;
import es.daw.jakarta.pedidosexamen.repository.PedidoDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListarPedidosServlet", value = "/pedidos/listar")
public class ListarPedidosServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ListarPedidosServlet.class.getName());

    PedidoDAO pedidoDAO = null;
    private GenericDAO<Cliente,Long>  clienteDAO = null;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Pedido> pedidos= new ArrayList<Pedido>();
        List<Cliente> clientes = new ArrayList<>();

        String clienteParam = request.getParameter("clienteId");
        String ordenEstado = request.getParameter("ordenEstado");

        try{

            clientes = clienteDAO.findAll();

            if(clienteParam != null && !clienteParam.isEmpty()){
                Long clienteId = Long.parseLong(clienteParam);
                pedidos = pedidoDAO.obtenerPedidosPorCliente(clienteId);
            }else{
                pedidos = pedidoDAO.findAll();
                clientes = clienteDAO.findAll();
            }

            if (ordenEstado != null) {
                if (ordenEstado.equals("asc")) {
                    pedidos.sort(Comparator.comparing(p -> p.getEstado().name()));
                } else if (ordenEstado.equals("desc")) {
                    pedidos.sort(Comparator.comparing((Pedido p) -> p.getEstado().name()).reversed());
                }
            }


            int pageSize = 0; // 0 = sin límite
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("pageSize".equals(cookie.getName())) {
                        try {
                            pageSize = Integer.parseInt(cookie.getValue());
                        } catch (NumberFormatException e) {
                            pageSize = 0;
                        }
                    }
                }
            }

            if (pageSize > 0 && pedidos.size() > pageSize) {
                pedidos = pedidos.subList(0, pageSize);
            }


            request.setAttribute("ordenEstado", ordenEstado);
            request.setAttribute("pedidos", pedidos);
            request.setAttribute("clientes", clientes);
            getServletContext().getRequestDispatcher("/pedidos.jsp").forward(request, response);
        }catch(SQLException e){
            request.setAttribute("mensajeError",
                    "Error al acceder a la base de datos: " + e.getMessage());

            // Redirigir con ruta absoluta dentro del contexto
            getServletContext()
                    .getRequestDispatcher("/error.jsp")
                    .forward(request, response);
            return;
        }
    }

}