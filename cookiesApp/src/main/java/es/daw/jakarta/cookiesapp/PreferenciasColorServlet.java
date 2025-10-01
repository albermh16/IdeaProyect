package es.daw.jakarta.cookiesapp;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/preferencias")
public class PreferenciasColorServlet extends HttpServlet {

    private static final String COOKIE_NAME = "colorFondo";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Borrar la preferencia... cookie
        String accion = request.getParameter("accion");

        if(accion != null){
            if(accion.equalsIgnoreCase("borrar")){
                Cookie cookie = new Cookie(COOKIE_NAME, null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Borrar la preferencia... crea la cookie o machaca el contenido de la cookie si ya existe

        String color = request.getParameter("color");


        if (color != null && !color.isBlank()) {
            // Crear una cookie
            Cookie cookie = new Cookie(COOKIE_NAME, color);
            // Establecer tiempo de expiración en 7 días
            cookie.setMaxAge(7 * 24 * 60 * 60);
            // Valida para todo el contexto del servidor
            cookie.setPath("/");
            // Solo podra ser accedida por el servidor http(s), pero NO VIA JS
            //cookie.setHttpOnly(true);
            response.addCookie(cookie);

        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }


}