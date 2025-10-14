package com.github.alberto.bibliotecajdbc.controllers.autores;

import com.github.alberto.bibliotecajdbc.model.Autor;
import com.github.alberto.bibliotecajdbc.model.Libro;
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

@WebServlet("/authors/list")
public class ListarAutoresServlet extends HttpServlet {

    LibroDAO daoLibro =  new LibroDAO();
    GenericDAO<Autor, Long> daoAutor;

    public ListarAutoresServlet() throws SQLException {
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

        List<Autor> autores = new ArrayList<Autor>();

        try{

            autores = daoAutor.findAll();

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        request.setAttribute("autores", autores);
        request.getRequestDispatcher("/autores/autores.jsp").forward(request, response);
    }

    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}