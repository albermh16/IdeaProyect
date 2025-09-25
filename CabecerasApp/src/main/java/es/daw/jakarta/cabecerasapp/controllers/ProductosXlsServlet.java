package es.daw.jakarta.cabecerasapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductosXlsServlet extends HttpServlet {
    //Pendiente completar





    @Override
    public void destroy() {
        super.destroy();
    }
}