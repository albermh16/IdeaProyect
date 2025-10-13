package com.github.alberto.bibliotecajdbc.controllers.libros;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
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

@WebServlet("/books/new")
public class añadirLibroServlet extends HttpServlet {

    GenericDAO<Libro, Long> daoLibro;
    GenericDAO<Autor, Long> daoAutor;

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

        List<Libro> libros = new ArrayList<Libro>();
        List<Autor> autores = new ArrayList<Autor>();

        try{

            autores = daoAutor.findAll();

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        request.setAttribute("autores", autores);
        request.getRequestDispatcher("/libros/formularioLibros.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long idLibro = Long.parseLong(request.getParameter("id"));
        if(idLibro == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String titulo = request.getParameter("title");
        if(titulo == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long idAutor = Long.parseLong(request.getParameter("author_id"));
        if(idAutor == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Date fecha = Date.valueOf(request.getParameter("publication_date"));
        if( fecha == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Libro libro = new Libro(idLibro,titulo,idAutor,fecha);

        try{

            daoLibro.save(libro);

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);

        }

        response.sendRedirect(request.getContextPath() + "/books/list");


    }

    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}