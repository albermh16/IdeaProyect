package es.daw.jakarta.pedidosexamen.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/preferencias")
public class CookiePageSize extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pageSize = req.getParameter("pageSize");

        Cookie cookie = new Cookie("pageSize", pageSize);
        cookie.setMaxAge(86400);
        cookie.setPath("/");
        res.addCookie(cookie);

        res.sendRedirect(req.getContextPath() + "/pedidos/listar");
    }
}
