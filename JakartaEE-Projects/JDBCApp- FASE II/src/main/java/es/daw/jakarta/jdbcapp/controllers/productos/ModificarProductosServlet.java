package es.daw.jakarta.jdbcapp.controllers.productos;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.DBConnection;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet(name = "ModificarProductosServlet", value = "/productos/editar")
public class ModificarProductosServlet extends HttpServlet {

    GenericDAO<Producto, Integer> daoP;
    GenericDAO<Fabricante, Integer> daoF;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            daoP=new ProductoDAO();
            daoF=new FabricanteDAO();

        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar el DAO", e);
        }
    }

    private static final Logger logger =  Logger.getLogger(ModificarProductosServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Fabricante> fabricantes = new ArrayList<>();
        try {

            String codigoParam = req.getParameter("codigo");

            if (codigoParam == null ||codigoParam.isBlank()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,  "Falta el parametro con el codigo del producto a actualizar");
                return;
            }

            try {
                Optional<Producto> producto = daoP.findById(Integer.parseInt(codigoParam));
                if (producto.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND,  "No existe el producto con el codigo: ");
                    return;
                }

                req.setAttribute("producto",  producto.get());

            }catch (SQLException ex) {
                // En una multipagina lo ideal es navegar, por tanto enviar a error.jsp... y desde ahi poder navegar "Volver"
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
            }

            fabricantes = daoF.findAll();
            req.setAttribute("fabricantes", fabricantes);

            req.getRequestDispatcher("/productos/formularioProducto.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            req.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);

        }
    }

    // listar productos
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // -----------------------------
        // Leer parámetros.... cuidadín si no valido los nulos, blancos o cosas no deseadas
        // HACER LAS COMPROBACIONES OPORTUNAS!!!


        Integer codigo = Integer.valueOf(request.getParameter("codigo") == null || request.getParameter("codigo").isBlank()? "" : request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        Integer codigo_fabricante = Integer.valueOf(request.getParameter("codigo_fabricante"));
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));

        Producto modificar =  new Producto(codigo, nombre, precio, codigo_fabricante);

        try{
            daoP.update(modificar);

        }catch(Exception ex){
            logger.severe(ex.getMessage());
            request.setAttribute("error", ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");

        }

        response.sendRedirect(request.getContextPath()+"/productos/ver");




    }



    @Override
    public void destroy() {
        logger.info("Destroy!!!!!!!!!!!!!1");
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
            // OBSERVACIÓN....
            // QUIERO REUTILIZAR ERROR.JSP PARA ENVIAR e.getMesage()
        }
    }
}