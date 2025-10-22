package es.daw.jakarta.jdbcapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/preferencias")
public class CookieProductosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String mostrar = req.getParameter("mostrar");

        Cookie cookie = new Cookie("mostrarProductos", mostrar);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            resp.addCookie(cookie);


        resp.sendRedirect(req.getContextPath() + "/fabricantes/ver");
    }


}
