package es.daw.jakarta.cabecerasapp.controllers;

import es.daw.jakarta.cabecerasapp.model.Producto;
import es.daw.jakarta.cabecerasapp.service.ProductoService;
import es.daw.jakarta.cabecerasapp.service.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet({"/productos.xls", "/productos.html", "/productos"})
public class ProductosXlsServlet extends HttpServlet {

    private ProductoService service = new ProductoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto> productos = service.listar();



        String servletPath = req.getServletPath();
        if (servletPath.endsWith(".xls")) {

            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");

            try (PrintWriter out = resp.getWriter()) {
                out.println("<table border=1>");
                out.println("<tr><th>ID</th><th>Nombre</th><th>Precio</th><th>tipo</th></tr>");
                for (Producto p : productos) {
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } else {
            req.setAttribute("productos", productos);
            getServletContext().getRequestDispatcher("/WEB-INF/productos.jsp").forward(req, resp);
        }
    }

}