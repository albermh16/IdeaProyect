package es.daw.jakarta.cookiesapp;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/preferencias")
public class PreferenciasColorServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Borrar la preferencia... cookie

    }


}