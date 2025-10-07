package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.DBConnection;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "ModificarProductosServlet", value = "/productos/modificar")
public class ModificarProductosServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ModificarProductosServlet.class.getName());

    // listar productos
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // -----------------------------
        // Leer parámetros.... cuidadín si no valido los nulos, blancos o cosas no deseadas
        // HACER LAS COMPROBACIONES OPORTUNAS!!!
        String codigo = request.getParameter("codigo") == null || request.getParameter("codigo").isBlank()? "" : request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String codigo_fabricante = request.getParameter("codigo_fabricante");
        String precio = request.getParameter("precio");

        // El mismo Servlet se encarga de insertar, borrar y actualizar
        // Vendrá con valores insert, update, y delate...
        String operacion =  request.getParameter("operacion");

        // Para borrar el código se mete en otro input
        String codigoBorrar = request.getParameter("codigoBorrar");


        try {
            GenericDAO<Producto, Integer> daoP = new ProductoDAO();
            GenericDAO<Fabricante, Integer> daoF = new FabricanteDAO();

            switch(operacion){
                case "insert":
                    try{
                        BigDecimal precioBig = new BigDecimal(precio);
                        Producto nuevoProd = new Producto(
                                Integer.parseInt(codigo),
                                nombre,
                                precioBig,
                                Integer.parseInt(codigo_fabricante)
                        );
                        daoP.save(nuevoProd);
                    }catch(NumberFormatException e){
                        request.setAttribute("error", "Has escrito mal el precio. Debe contener numeros, y si contuviese decimales con punto. Ej: 120.50");
                        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;

                case "update":
                    try{
                        BigDecimal precioBig = new BigDecimal(precio);
                        Producto nuevoProd = new Producto(
                                Integer.parseInt(codigo),
                                nombre,
                                precioBig,
                                Integer.parseInt(codigo_fabricante)
                        );
                        daoP.update(nuevoProd);
                    }catch(NumberFormatException e){
                        request.setAttribute("error", "Has escrito mal el precio. Debe contener numeros, y si contuviese decimales con punto. Ej: 120.50");
                        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                    break;

                case "delete":
                    try{
                        daoP.delete(Integer.parseInt(codigo));
                    }catch(NumberFormatException e){
                        request.setAttribute("error", "Debes indicar un codigo para borrar");
                        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                    }
            }

            List<Producto> productos = daoP.findAll();
            List<Fabricante> fabricantes = daoF.findAll();

            productos.sort((p1, p2) -> p2.getCodigo().compareTo(p1.getCodigo())); //ORDENACION POR CODIGO ASCENCENTE
            request.setAttribute("productos", productos);
            request.setAttribute("fabricantes", fabricantes);
            request.setAttribute("message", "Operacion <" +operacion+">realizada con exito!");
            getServletContext().getRequestDispatcher("/informe.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void destroy() {
        log.info("Destroy!!!!!!!!!!!!!1");
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
            // OBSERVACIÓN....
            // QUIERO REUTILIZAR ERROR.JSP PARA ENVIAR e.getMesage()
        }
    }
}