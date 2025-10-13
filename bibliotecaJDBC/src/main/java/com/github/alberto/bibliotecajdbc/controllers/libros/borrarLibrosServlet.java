package com.github.alberto.bibliotecajdbc.controllers.libros;

import com.github.alberto.bibliotecajdbc.model.Autor;
import com.github.alberto.bibliotecajdbc.model.Libro;
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

@WebServlet("/books/delete")
public class borrarLibrosServlet extends HttpServlet {

    GenericDAO<Libro, Long> daoLibro;
    GenericDAO<Autor, Long> daoAutor;

    public borrarLibrosServlet() throws SQLException {
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try{
            daoLibro = new LibroDAO();

        }catch(Exception e){
            throw new RuntimeException("Error al inicializar el DAO",e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long libroId = Long.parseLong(request.getParameter("id"));

        try{

            daoLibro.delete(libroId);

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/books/list" );

    }

    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}