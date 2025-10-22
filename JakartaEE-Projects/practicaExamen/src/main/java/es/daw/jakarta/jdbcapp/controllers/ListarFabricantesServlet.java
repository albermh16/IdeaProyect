package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.DBConnection;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "ListarFabricantesServlet", value = "/fabricantes/ver")
public class ListarFabricantesServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ListarFabricantesServlet.class.getName());

    // listar fabricantes
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Fabricante> fabricantes = new ArrayList<>(); // no es nula, está vacía
        List<Producto> productos = new ArrayList<>();

        try {
            GenericDAO<Fabricante,Integer> daoF = new FabricanteDAO();
            GenericDAO<Producto,Integer> daoProducto = new ProductoDAO();

            String filtrar =  request.getParameter("buscar");
            FabricanteDAO fabricanteDAO = new FabricanteDAO();

            if( filtrar != null && !filtrar.isEmpty()) {
                fabricantes = fabricanteDAO.findByName(filtrar);
            }else{
                fabricantes = daoF.findAll();
            }

            productos = daoProducto.findAll();
            fabricantes.forEach(f -> log.info(f.toString()));

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean mostrar = true;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("mostrarProductos")){
                    mostrar = "si".equals(cookie.getValue());
                }
            }
        }

        request.setAttribute("mostrar", mostrar);
        request.setAttribute("productos", productos);
        request.setAttribute("fabricantes", fabricantes);

        getServletContext().getRequestDispatcher("/fabricantes.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

    @Override
    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
        }
    }
}