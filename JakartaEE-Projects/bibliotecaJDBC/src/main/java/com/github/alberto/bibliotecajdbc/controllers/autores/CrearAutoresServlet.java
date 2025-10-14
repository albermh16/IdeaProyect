package com.github.alberto.bibliotecajdbc.controllers.autores;

import com.github.alberto.bibliotecajdbc.model.Autor;
import com.github.alberto.bibliotecajdbc.repository.AutorDAO;
import com.github.alberto.bibliotecajdbc.repository.DBConnection;
import com.github.alberto.bibliotecajdbc.repository.GenericDAO;
import com.github.alberto.bibliotecajdbc.repository.LibroDAO;
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

@WebServlet("/authors/new")
public class CrearAutoresServlet extends HttpServlet {

    LibroDAO daoLibro =  new LibroDAO();
    GenericDAO<Autor, Long> daoAutor;

    public CrearAutoresServlet() throws SQLException {
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try{
            daoLibro = new LibroDAO();
            daoAutor = new AutorDAO();
        }catch(Exception e){
            throw new RuntimeException("Error al inicializar el DAO",e);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/autores/formularioAutores.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String id = req.getParameter("id");
            if (id == null || id.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El identificador no puede estar vacio");
            }
            Long idAutor = Long.parseLong(id);

            String nombre = req.getParameter("name");
            if (nombre == null || nombre.isEmpty() || nombre.length() > 255) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre no puede estar vacio o tener mas de 255 caracteres");
            }

            Autor autores = new Autor(idAutor, nombre);

            daoAutor.save(autores);

            req.setAttribute("autores", autores);
            resp.sendRedirect(req.getContextPath() + "/authors/list");

        }catch (Exception e){
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);

        }



    }

    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}