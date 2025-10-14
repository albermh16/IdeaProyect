<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cookie</title>
</head>
<%
    String color = "#ffffff";
    final String COOKIE_NAME = "colorFondo"; // pendiente final static en JSP ???????

    if(request.getCookies() != null){
        for(Cookie cookie: request.getCookies()){
            if(cookie.getName().equals(COOKIE_NAME)){
                color = cookie.getValue();
                break;
            }
        }
    }
%>
<body style="background-color: <%=color%>">

<h1><%= "Bienvenido" %>
</h1>
<br/>
<p>Color del fondo actual: <strong><%=color%></strong></p>
<p>Quieres cambiar el color de fondo?</p>
<ul>
    <li><a href="color.jsp">Si, cambiar color</a></li>
    <li><a href="preferencias?accion=borrar">Borrar la preferencia </a></li>
</ul>

</body>
</html>