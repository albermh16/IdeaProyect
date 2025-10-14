package com.github.alberto.bibliotecajdbc.controllers.libros;

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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/books/edit")
public class ModificarLibrosServlet extends HttpServlet {

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

        List<Autor> autores = new ArrayList<Autor>();

        try{

            String id = request.getParameter("id");

            if(id == null || id.isEmpty()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el id del libro para actualizar");
                return;
            }

            try{
                Optional<Libro> libro =daoLibro.findById(Long.parseLong(id));
                if(libro.isEmpty()){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se ha encontrado el libro con el id: "+ id);
                    return;
                }

                request.setAttribute("libro", libro.get());
            }catch(SQLException e){
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            autores = daoAutor.findAll();

        }catch(Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        request.setAttribute("autores", autores);
        request.getRequestDispatcher("/libros/formularioLibros.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            String idAutorParam = req.getParameter("author_id");
            String titulo = req.getParameter("title");
            String fechaParam = req.getParameter("publication_date");

            if (titulo.isEmpty() || titulo.length() > 255) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El titulo es obligatorio. No debe pasar de los 255 caracteres");
            }

            //COMPROBAR SI EXISTE ID DEL AUTOR EN LA BBDD
            if (idAutorParam == null || idAutorParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el id del autor para actualizar");
            }
            Long idAutor = Long.parseLong(idAutorParam);


            Optional<Autor> autorExiste = daoAutor.findById(idAutor);
            if (autorExiste.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El autor con id: " + idAutor + " no existe");
            }


            //FECHA
            if (fechaParam == null || fechaParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta la fecha para actualizar");
            }

            Date fecha = null;
            try {
                fecha = Date.valueOf(fechaParam);
            } catch (IllegalArgumentException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha invalido");
            }

            Libro libro = new Libro(id, titulo, idAutor, fecha);

            daoLibro.update(libro);

            resp.sendRedirect(req.getContextPath() + "/books/list");

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