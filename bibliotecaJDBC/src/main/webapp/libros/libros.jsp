<%@ page import="com.github.alberto.bibliotecajdbc.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Libros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #1a1d23;
        }
        .navbar-brand {
            color: #ffffff !important;
            font-weight: 600;
        }
        .table thead {
            background-color: #1a1d23;
            color: white;
        }
        .btn-primary {
            background-color: #0d6efd;
            border: none;
        }
        .btn-warning {
            background-color: #ffc107;
            border: none;
        }
        .btn-danger {
            background-color: #dc3545;
            border: none;
        }
        .btn-success {
            background-color: #198754;
            border: none;
        }
        .card {
            border: none;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark px-3">
    <a class="navbar-brand" href="/welcome.jsp">Biblioteca</a>
</nav>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="fw-bold text-dark">Libros disponibles</h3>
        <a href="/books/new" class="btn btn-success">
            <i class="bi bi-plus-lg"></i> Nuevo libro
        </a>
    </div>

    <!-- Mensajes de éxito o error -->
    <!--
    <%--
    <%
        String successMessage = request.getAttribute("successMessage").toString();
        String errorMessage = request.getAttribute(("errorMessage")).toString();

        if(successMessage != null){
    %>
            <div class = "alert alert-success"><%=successMessage%></div>
    <%
            request.removeAttribute("successMessage"º);
        }

        if(errorMessage != null){
    %>
            <div class = "alert alert-danger"><%=errorMessage%></div>
    <%
            request.removeAttribute("errorMessage");
        }
    %>
    --%>
    -->


    <!-- Botón de filtrado -->
    <form class="d-flex mb-3" method="get" action="/books/list">
        <input type="text" class="form-control me-2" name="filter" placeholder="Filtrar por título o autor">
        <button class="btn btn-primary" type="submit">Filtrar</button>
    </form>
    <%
        List<Libro> libros = (List<Libro>) request.getAttribute("libros");
    %>

    <!-- Tabla de libros -->
    <div class="card p-3">
        <table class="table table-striped table-hover align-middle">
            <thead>
            <tr>
                <th>Título</th>
                <th>Autor</th>
                <th>Fecha</th>
                <th>Editar</th>
                <th>Borrar</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(Libro lib: libros){
            %>
                <tr>
                    <td><%=lib.getTitulo()%></td>
                    <td><%=lib.getAutor()%></td>
                    <td><%=lib.getFecha_publicacion()%></td>
                    <td>

                        <a href="/books/edit?id=${book.id}" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil"></i>
                        </a>
                    </td>
                    <td>
                        <a href="/books/delete?id=${book.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Seguro que deseas borrar este libro?');">
                            <i class="bi bi-trash"></i>
                        </a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <!-- Botón volver -->
    <div class="mt-4">
        <a href="/welcome.jsp" class="btn btn-secondary">
            <i class="bi bi-arrow-left"></i> Volver al inicio
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
