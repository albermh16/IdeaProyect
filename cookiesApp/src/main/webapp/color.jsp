<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cambiar color</title>
</head>
<body>
<h1>Cambiar color de fondo</h1>

<form action="preferencias" method="post">
    <label for="color">Elige un color:</label>
    <input type="color" id="color" name="color" value="#ffffff" />
    <input type="hidden" name="accion" value="guardar" />
    <button type="submit">Guardar preferencia</button>
</form>

<p><a href="index.jsp">Volver a la página principal</a></p>
</body>
</html>
