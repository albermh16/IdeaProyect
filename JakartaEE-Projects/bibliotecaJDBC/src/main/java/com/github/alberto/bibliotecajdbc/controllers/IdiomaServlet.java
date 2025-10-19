package com.github.alberto.bibliotecajdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/es", "/en"})
public class IdiomaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String lang = uri.endsWith("/en") ? "en" : "es";

        Cookie cookie = new Cookie("lang", lang);
        cookie.setMaxAge(60*60*24*30);
        resp.addCookie(cookie);

        req.setAttribute("lang", lang);

        resp.sendRedirect(req.getContextPath() + "/welcome.jsp");
    }

}
