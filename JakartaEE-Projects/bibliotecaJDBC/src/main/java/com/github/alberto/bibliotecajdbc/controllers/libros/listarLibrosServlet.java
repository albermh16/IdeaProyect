package com.github.alberto.bibliotecajdbc.controllers.libros;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.alberto.bibliotecajdbc.model.Autor;
import com.github.alberto.bibliotecajdbc.model.Libro;
import com.github.alberto.bibliotecajdbc.repository.AutorDAO;
import com.github.alberto.bibliotecajdbc.repository.DBConnection;
import com.github.alberto.bibliotecajdbc.repository.GenericDAO;
import com.github.alberto.bibliotecajdbc.repository.LibroDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/books/list")
public class listarLibrosServlet extends HttpServlet {

    LibroDAO daoLibro =  new LibroDAO();
    GenericDAO<Autor, Long> daoAutor;

    public listarLibrosServlet() throws SQLException {
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

        String filter = request.getParameter("filter");
        List<Autor> autores = new ArrayList<Autor>();
        List<Libro> libros = new ArrayList<>();

        try{

            autores = daoAutor.findAll();

            if(filter != null && !filter.isEmpty()){
                libros = daoLibro.findByTitleOrAuthor(filter);
            }else{
                libros = daoLibro.findAll();
            }

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        request.setAttribute("autores", autores);
        request.setAttribute("libros", libros);
        request.getRequestDispatcher("/libros/libros.jsp").forward(request, response);
    }

    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}