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
import java.util.Optional;

@WebServlet("/authors/edit")
public class ModificarAutoresServlet extends HttpServlet {

    ;
    GenericDAO<Autor, Long> daoAutor;

    public ModificarAutoresServlet(){

    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try{

            daoAutor = new AutorDAO();
        }catch(Exception e){
            throw new RuntimeException("Error al inicializar el DAO",e);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            String id = request.getParameter("id");

            System.out.println("id recibido: " + id);
            if(id == null){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Introduce el id del autor");
                return;
            }

            try{
                System.out.println("Buscando autor en BD");
                Optional<Autor> autor = daoAutor.findById(Long.parseLong(id));
                System.out.println("Autor encontrado" + autor);
                if(autor.isEmpty()){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Autor inexistente");
                    return;
                }

                request.setAttribute("autor", autor.get());
                System.out.println("Autor encontrado, enviando al JSP...");

            }catch (Exception e){
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

        }catch (Exception e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return;
        }

        request.getRequestDispatcher("/autores/formularioAutores.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            System.out.println("Entrando en el doPost");
            Long id = Long.parseLong(req.getParameter("id"));
            String nombre = req.getParameter("name");

            Autor autor= new Autor(id, nombre);

            daoAutor.update(autor);
            System.out.println("Autor modificado: " + autor);
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