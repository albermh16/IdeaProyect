package es.daw.jakarta.formpost;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/registro")
public class HelloServlet extends HttpServlet {
    private String message;
    private static final Logger logger = Logger.getLogger(HelloServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        // 1. Lectura de parametros del request
        String username = req.getParameter("username");
        // No esta permitido espacios
        if (username != null) {
            //username = username.replace(" ", "");
            //username = username.trim(); // quita espacios izq y derecha
            username = username.replaceAll("\\s", "");
        }
        logger.info("username: " + username);
        String password = req.getParameter("password");
        logger.info("password: " + password);
        String email = req.getParameter("email");
        logger.info("email: " + email);
        String pais = req.getParameter("pais");
        logger.info("pais: " + pais);

        String[] lenguajes = req.getParameterValues("lenguajes");
        if (lenguajes != null) {
            logger.info("lenguajes 1: " + Arrays.toString(lenguajes));
            logger.info("lenguajes 2: " + lenguajes.toString());
            logger.info("lenguajes 3: " + lenguajes);
        }

        String[] roles = req.getParameterValues("roles");
        if (roles != null) {
            logger.info("roles: " + Arrays.toString(roles));
        }
        logger.info("roles: " + roles);
        String idioma = req.getParameter("idioma");
        logger.info("idioma: " + idioma);
        String habilitar = req.getParameter("habilitar");
        logger.info("habilitar: " + habilitar);
        String secreto = req.getParameter("secreto");
        logger.info("secreto: " + secreto);

        // 2. Comprobaciones para generar errores

        ArrayList<String> errores = new ArrayList<>();
        //List<String> errores2 = new ArrayList<>();

        //Confirmar diferencia isBlank isEmpty...
        if (username == null || username.isBlank()) {
            errores.add("El usuario es obligatorio");
        }
        if (password == null || password.isBlank()) {
            errores.add("La contraseña es obligatoria");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            errores.add("El email es obligatorio y debe contener @");
        }
        if (idioma == null) {
            errores.add("Debes seleccionar un idioma!");
        }

        // 3. Generar HTML de respuesta

        //Devolver mensaje de texto
        //resp.getWriter().append("Server at: ").append(req.getContextPath());
        resp.setContentType("text/html");

        try (PrintWriter out = resp.getWriter()) {
            // usando literal template
            out.println("""
                    <html>
                    <head>
                        <title>ParamsFormServlet</title>
                    </head>
                    <body>
                        <h1>Informe de datos recibidos del formulario</h1>
                        <ul>
                    """);
            if (!errores.isEmpty()) {
                //errores.forEach(err -> out.println("<li>" + err + "</li>"));
                errores.forEach(error -> out.printf("<li>%s</li>\n", error));
            } else {
                //El resto de campos...
                String lenguajesHtml = "";
                for (String lenguaje : lenguajes) {
                    //lenguajesHtml += "<li>"+lenguaje+"</li>\n";
                    lenguajesHtml = lenguajesHtml.concat("<li>" + lenguaje + "</li>\n");
                }
                ;

                String rolesHtml = Arrays.stream(roles)
                        .map(rol -> rol + "\n")
                        .collect(Collectors.joining("\n"));

                //Si no hay errores pinto items en la lista con los valores de los campos
                String htmlBody = """
                        <li>Username: %s</li>
                        <li>Password: %s</li>
                        <li>Email: %s</li>
                        <li>Idioma: %s</li>
                        <li>Lenguajes: %s</li>                       
                        <li>roles: 
                            <ul>%s</ul>
                        </li>
                        <li>habilitar: %s</li>
                        <li>secreto: %s</li>                                                                 
                        """.formatted(username, password, email, idioma, lenguajesHtml, rolesHtml, habilitar, secreto);
                out.println((htmlBody));
            }
            ;

            out.println("""
                            </ul>
                            <p><a href="index.jsp">volver</a></p>
                        </body>
                    </html>
                    """);
        }
        ;


    }
}