package com.github.alberto.bibliotecajdbc.controllers.libros;

import java.io.*;
import java.sql.Date;
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

        HttpSession session = request.getSession(false);
        if (session != null) {
            String success = (String) session.getAttribute("successMessage");
            String error = (String) session.getAttribute("errorMessage");

            if (success != null) {
                request.setAttribute("successMessage", success);
                session.removeAttribute("successMessage");
            }

            if(error != null){
                request.setAttribute("errorMessage", error);
                session.removeAttribute("errorMessage");
            }
        }

        String filter = request.getParameter("filter");
        String yearFrom = request.getParameter("yearFrom");
        String yearTo = request.getParameter("yearTo");

        List<Autor> autores = new ArrayList<Autor>();
        List<Libro> libros = new ArrayList<>();

        try{

            autores = daoAutor.findAll();

            if (yearFrom != null && !yearFrom.isEmpty() && yearTo != null && !yearTo.isEmpty()) {
                int from = Integer.parseInt(yearFrom);
                int to = Integer.parseInt(yearTo);

                Date fromDate = Date.valueOf(from + "-01-01");
                Date toDate = Date.valueOf(to + "-12-31");

                libros = daoLibro.findByPublicationDateRange(fromDate, toDate);

            } else if(filter != null && !filter.isEmpty()){

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