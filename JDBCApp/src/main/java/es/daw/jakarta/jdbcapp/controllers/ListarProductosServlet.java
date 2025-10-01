package es.daw.jakarta.jdbcapp.controllers;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import es.daw.jakarta.jdbcapp.repository.DBConnection;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/productos/ver")
public class ListarProductosServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ListarProductosServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Connection conexion = null;

        try{
            conexion = DBConnection.getConnection();
            log.info("* CONENECTION: " + conexion);
        } catch (SQLException e){
            log.severe("* ERROR: " + e.getMessage());

            throw new RuntimeException(e);
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}