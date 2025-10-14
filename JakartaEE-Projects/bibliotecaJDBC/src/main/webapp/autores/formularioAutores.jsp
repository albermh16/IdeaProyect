<%@ page import="com.github.alberto.bibliotecajdbc.model.Autor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario Autor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">

<%
    Autor autor = (Autor) request.getAttribute("autores");
    boolean edicion = autor != null;

    String accion = edicion ? "edit" : "new";
    String titulo = edicion ? "Editar autor" : "Nuevo autor";

%>

<div class="container my-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px; border-radius: 15px;">
        <div class="card-body">
            <h2 class="text-center mb-4 fw-bold text-primary"><%=titulo%></h2>

            <form action="<%=request.getContextPath()%>/authors/<%=accion%>" method="post">

                <!-- ID -->
                <div class="mb-3">
                    <label for="id" class="form-label fw-semibold">ID del autor</label>
                    <input type="text" class="form-control" id="id" name="id" placeholder="Ej: 1"
                    value="<%=edicion ? autor.getId() : ""%>" <%=edicion ? "readonly" : ""%>>
                    <small class="text-muted">El ID identifica de forma única al autor en la base de datos.
                        <% if (edicion) { %>
                        (No se puede modificar)
                        <% } %></small>
                </div>

                <!-- Nombre -->
                <div class="mb-3">
                    <label for="name" class="form-label fw-semibold">Nombre del autor</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Ej: Gabriel García Márquez"
                    value="<%=edicion ? autor.getNombre() : ""%>">
                </div>

                <!-- Botones -->
                <div class="d-flex justify-content-between mt-4">
                    <a href="<%=request.getContextPath()%>/authors/list" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-circle"></i> Cancelar
                    </a>
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-save"></i> Guardar autor
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
