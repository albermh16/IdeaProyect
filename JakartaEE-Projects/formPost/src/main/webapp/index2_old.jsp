<%@ page import="java.util.Map" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1><%= "Formulario de usuarios JSP" %>
</h1>

<!-- Mostramos lista de errores, tipo informe -->
<%
    // El servlet que se ejecute desde el formulario va a redirigir la salida a la página JSP
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");

    String usernameVal = request.getParameter("username") != null ? request.getParameter("username") : "";
    String passwordVal = request.getParameter("password") != null ? request.getParameter("password") : "";
    String emailVal = request.getParameter("email") != null ? request.getParameter("email") : "";
    String paisVal = request.getParameter("pais") != null ? request.getParameter("pais") : "";
    String[] lenguajesVal = request.getParameterValues("lenguajes") != null ? request.getParameterValues("lenguajes") : new String[]{""};

%>


<% if (errores != null && !errores.isEmpty()) { %>
<ul>
    <!-- genero tantos li como errores haya -->
    <% for (String error : errores.values()) {%>
    <li><%=error%>
    </li>
    <%}%>
</ul>
<% } %>

<%--<%--%>
<%--    // OTRA FORMA DE HACERLO PERO LAS ETIQUETAS HTML ESTÁN COMO TEXTO... SE MANEJA PEOR--%>
<%--    if (errores != null && !errores.isEmpty()) {--%>
<%--        StringBuilder sb = new StringBuilder("<ul>");--%>
<%--        for (String error : errores.values()) {--%>
<%--            sb.append("<li>").append(error).append("</li>");--%>
<%--        }--%>
<%--        sb.append("</ul>");--%>
<%--        out.print(sb.toString());--%>
<%--    }--%>
<%--%>--%>

<form action="registro2" method="post">

    <div>
        <label for="username">Usuario</label>
        <div><input type="text" name="username" id="username" value="<%=usernameVal%>"/></div>
        <!-- mostraremos un div con el mensaje de error -->
        <%
            if (errores != null && errores.containsKey("username")) {
                out.println("<div style='color:red'>" + errores.get("username") + "</div>");
            }
        %>
    </div>
    <div>
        <label for="password">Password</label>
        <div><input type="password" name="password" id="password" value=<%=passwordVal%>></div>
        <!-- PENDIENTE COMPROBAR SI ERROR!!!! -->
        <%
            if (errores != null && errores.containsKey("password")) {
                out.println("<div style='color:red'>" + errores.get("password") + "</div>");
            }
        %>
    </div>
    <div>
        <label for="email">Email</label>
        <div><input type="text" name="email" id="email" value=<%=emailVal%>></div>
        <%
            if (errores != null && errores.containsKey("email")) {
                out.println("<div style='color:red'>" + errores.get("email") + "</div>");
            }
        %>
    </div>
    <div>
        <label for="pais">País</label>
        <div>
            <select name="pais" id="pais">
                <option value="" <%= paisVal.equals("") ? "selected" : "" %>>-- seleccionar --</option>
                <option value="ES" <%= paisVal.equals("ES") ? "selected" : "" %>>España</option>
                <option value="MX" <%= paisVal.equals("MX") ? "selected" : "" %>>México</option>
                <option value="CL" <%= paisVal.equals("CL") ? "selected" : "" %>>Chile</option>
                <option value="AR" <%= paisVal.equals("AR") ? "selected" : "" %>>Argentina</option>
                <option value="PE" <%= paisVal.equals("PE") ? "selected" : "" %>>Perú</option>
                <option value="CO" <%= paisVal.equals("CO") ? "selected" : "" %>>Colombia</option>
                <option value="VE" <%= paisVal.equals("VE") ? "selected" : "" %>>Venezuela</option>
            </select>
            <%
                if (errores != null && errores.containsKey("pais")) {
                    out.println("<div style='color:red'>" + errores.get("pais") + "</div>");
                }
            %>
        </div>
    </div>

    <div>
        <label for="lenguajes">Lenguajes de programación</label>
        <div>
            <select name="lenguajes" id="lenguajes" multiple>
                <option value="java"<%=Arrays.asList(lenguajesVal).contains("java") ? "selected" : ""%>>Java SE
                </option>
                <option value="jakartaee"<%=Arrays.asList(lenguajesVal).contains("jakartaee") ? "selected" : ""%>>
                    Jakarta EE9
                </option>
                <option value="spring"<%=Arrays.asList(lenguajesVal).contains("spring") ? "selected" : ""%>>Spring
                    Boot
                </option>
                <option value="js"<%=Arrays.asList(lenguajesVal).contains("js") ? "selected" : ""%>>JavaScript
                </option>
                <option value="angular" <%=Arrays.asList(lenguajesVal).contains("angular") ? "selected" : ""%>>Angular
                </option>
                <option value="react" <%=Arrays.asList(lenguajesVal).contains("react") ? "selected" : ""%>>React
                </option>
            </select>
            <%
                if (errores != null && errores.containsKey("lenguajes")) {
                    out.println("<div style='color:red'>" + errores.get("lenguajes") + "</div>");
                }
            %>
        </div>
    </div>

    <div>
        <label>Roles</label>
        <div>
            <input type="checkbox" name="roles" value="ROLE_ADMIN">
            <label>Administrador</label>
        </div>
        <div>
            <input type="checkbox" name="roles" value="ROLE_USER" checked>
            <label>Usuario</label>
        </div>
        <div>
            <input type="checkbox" name="roles" value="ROLE_MODERATOR">
            <label>Moderador</label>
        </div>
    </div>
    <div>
        <label>Idiomas</label>
        <div>
            <input type="radio" name="idioma" value="es">
            <label>Español</label>
        </div>
        <div>
            <input type="radio" name="idioma" value="en">
            <label>Inglés</label>
        </div>
        <div>
            <input type="radio" name="idioma" value="fr">
            <label>Frances</label>
        </div>
    </div>
    <div>
        <label for="habilitar">Habilitar</label>
        <div>
            <input type="checkbox" name="habilitar" id="habilitar" checked>
        </div>
    </div>
    <div>
        <div>
            <input type="submit" value="Enviar">
        </div>
    </div>
    <input type="hidden" name="secreto" value="12345">
</form>
</body>
</html>