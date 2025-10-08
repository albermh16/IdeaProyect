<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Fabricante" %>
<%@ page import="java.util.List" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Producto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 600px;
            margin: 3rem auto;
            padding: 2rem;
            border-radius: 1rem;
            background: #fff;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <%
        Fabricante fabricante = (Fabricante) request.getAttribute("fabricante");
        boolean esEdicion = fabricante != null;

        String titulo = esEdicion ? "Editar producto" : "Nuevo Producto";
        String accion = esEdicion ? "actualizar" : "crear";

    %>

    <div class="mb-3">
        <label for="codigo" class="form-label">Código del producto</label>
        <input type="text" id="codigo" name="codigo" class="form-control"
               required placeholder="Ej: 101"
               value="<%= esEdicion ? fabricante.getCodigo() : "" %>"
            <%= esEdicion ? "readonly" : "" %>>
        <div class="form-text text-muted">
            El código identifica de forma única al producto en la base de datos.
            <% if (esEdicion) { %>
            (No se puede modificar)
            <% } %>
        </div>
    </div>
    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre del producto</label>
        <input type="text" id="nombre" name="nombre" class="form-control" required placeholder="Ej: Portátil Lenovo"
               value = "<%= esEdicion? fabricante.getNombre():""%>"/>
    </div>


</body>
</html>
