package com.github.alberto.bibliotecajdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginWithCookie extends HttpServlet {

    final static String user1 = "admin";
    final static String pass1 = "admin";
    final static String user2 = "master";
    final static String pass2 = "master";

    final static String cookie_color = "colorFondo";



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String usuario = req.getParameter("username");
        String password = req.getParameter("password");
        String color = req.getParameter("color");

        if(usuario == null || password == null || color == null){
            req.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        boolean valido = false;

        if(user1.equals(usuario) && pass1.equals(password)){
            valido = true;
        }else if(user2.equals(usuario) && pass2.equals(password)){
            valido = true;
        }
        if(valido){
            Cookie userCookie =  new Cookie("userLoged", usuario);
            Cookie colorCookie = new Cookie("colorFavorito", color);

            userCookie.setMaxAge(3600);
            colorCookie.setMaxAge(3600);

            userCookie.setPath("/");
            colorCookie.setPath("/");

            resp.addCookie(userCookie);
            resp.addCookie(colorCookie);

            resp.sendRedirect(req.getContextPath() + "/welcome.jsp");
        }else{
            req.setAttribute("errorMessage", "Usuario o contraseña incorrectos");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }






    }
}
