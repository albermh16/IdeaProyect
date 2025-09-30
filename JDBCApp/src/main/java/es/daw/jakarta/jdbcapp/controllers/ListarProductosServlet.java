package es.daw.jakarta.jdbcapp.controllers;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListarProductosServlet", value = "/productos/ver")
public class ListarProductosServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }
}