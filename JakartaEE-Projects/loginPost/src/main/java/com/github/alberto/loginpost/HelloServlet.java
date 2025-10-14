package com.github.alberto.loginpost;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/login")
public class HelloServlet extends HttpServlet {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (USERNAME.equals(login) && PASSWORD.equals(password)) {

            req.setAttribute("usuario", login);
            req.getRequestDispatcher("success.jsp").forward(req, res);

        }else{

            res.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos, no está autorizado para ingresar a esta página!");
        }
    }


    }
