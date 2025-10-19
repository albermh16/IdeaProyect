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
        HttpSession session = request.getSession();

        try{

            String idParam = request.getParameter("id");
            String titulo = request.getParameter("title");
            String autorIdParam = request.getParameter("author_id");
            String fechaParam = request.getParameter("publication_date");

            if(idParam == null || idParam.isBlank() ||
                    titulo == null || titulo.isBlank() ||
                    fechaParam == null || fechaParam.isBlank() ||
                    autorIdParam == null || autorIdParam.isBlank()){

                session.setAttribute("errorMessageForm", "Todos los campos son obligatorios");

                List<Autor> autores = daoAutor.findAll();
                request.setAttribute("autores", autores);
                request.getRequestDispatcher("/libros/formularioLibros.jsp").forward(request, response);
                return;
            }

            Long idLibro = Long.parseLong(idParam);
            Long autorId = Long.parseLong(autorIdParam);
            Date fecha = Date.valueOf(fechaParam);

            if(titulo.length() < 3){
                session.setAttribute("errorMessage", "La titulo debe ter no minimo 3 letras");
                response.sendRedirect(request.getContextPath()+"/books/new");
                return;
            }


            Libro libro = new Libro(idLibro,titulo,autorId,fecha);

            daoLibro.save(libro);

            request.setAttribute("libro", libro);
            session.setAttribute("successMessageForm","Libro añadido correctamente");

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            session.setAttribute("errorMessage","Error al crear libros");

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