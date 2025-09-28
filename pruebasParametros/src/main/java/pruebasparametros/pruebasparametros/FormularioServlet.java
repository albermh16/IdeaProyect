package pruebasparametros.pruebasparametros;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/FormularioServlet")
public class FormularioServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String pais = request.getParameter("pais");
        String idioma = request.getParameter("idioma");
        String[] lenguajes = request.getParameterValues("lenguajes");
        String[] roles = request.getParameterValues("roles");
        boolean habilitar = request.getParameter("habilitar") != null;
        String secreto = request.getParameter("secreto");

        StringBuilder errores = new StringBuilder();

        errores.append(username == null || username.trim().isEmpty() ? "<li>El nombre de usuario es obligatorio.</li>" : "");
        errores.append(password == null || password.trim().isEmpty() ? "<li>La contraseña es obligatoria.</li>" : "");
        errores.append(email == null || !email.contains("@") ? "<li>El correo es obligatorio y debe contener '@' </li>" : "");
        errores.append(idioma == null ? "<li>Debe seleccionar un idioma</li>" : "");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='utf-8'><title>Resultado</title></head><body>");

            if (errores.length() > 0) {
                out.println("<h1>Errores en el formulario</h1>");
                out.println("<ul>" + errores + "</ul>");
                out.println("<p><a href='index.jsp'>Volver</a></p>");
            } else {
                out.println("<h1>Formulario enviado correctamente</h1>");
                out.println("<p><strong>Usuario:</strong> " + username + "</p>");
                out.println("<p><strong>Email:</strong> " + email + "</p>");
                out.println("<p><strong>País:</strong> " + pais + "</p>");
                out.println("<p><strong>Lenguajes:</strong> " + lenguajes + "</p>");
                out.println("<p><strong>Roles:</strong> " + roles + "</p>");
                out.println("<p><strong>Idioma:</strong> " + idioma + "</p>");
                out.println("<p><strong>Habilitado:</strong> " + (habilitar ? "Si" : "No") + "</p>");
                out.println("<p><strong>Secreto:</strong> " + secreto + "</p>");
                out.println("<p><a href='index.jsp'>Volver</a></p>");
            }

            out.println("</body></html>");

        }


    }

    public void destroy() {
    }
}