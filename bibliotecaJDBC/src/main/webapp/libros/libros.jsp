<%@ page import="com.github.alberto.bibliotecajdbc.model.Libro" %>
<%@ page import="java.util.List" %>
<%@ page import="com.github.alberto.bibliotecajdbc.model.Autor" %>
<%@ page import="com.github.alberto.bibliotecajdbc.utils.Utils" %>
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
    <a class="navbar-brand" href="<%=request.getContextPath()%>">Biblioteca</a>
</nav>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="fw-bold text-dark">Libros disponibles</h3>
        <a href="<%=request.getContextPath()%>/books/new" class="btn btn-success">
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
    <%

        List<Libro> libros = (List<Libro>) request.getAttribute("libros");
        List<Autor> autores = (List<Autor>) request.getAttribute("autores");

        if(libros !=null || !libros.isEmpty()){
    %>

    <!-- Botón de filtrado -->
    <form class="d-flex mb-3" method="get" action="<%=request.getContextPath()%>/books/list">
        <input type="text" class="form-control me-2" name="filter" placeholder="Filtrar por título o autor"
        value="<%=request.getParameter("filter") != null ? request.getParameter("filter") : ""%>">
        <button class="btn btn-primary" type="submit">Filtrar</button>
    </form>


    <!-- Tabla de libros -->
    <div class="card p-3">
        <table class="table table-striped table-hover align-middle">
            <thead>
            <tr>
                <th>Codigo</th>
                <th>Título</th>
                <th>Autor</th>
                <th>Fecha de publicacion</th>
                <th>Editar</th>
                <th>Borrar</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(Libro lib: libros){
                    String nombreAutor = Utils.obtenerNombreAutor(autores, lib.getAuthor_id());
            %>
                <tr>
                    <td><%=lib.getId()%></td>
                    <td><%=lib.getTitle()%></td>
                    <td><%=nombreAutor%></td>
                    <td><%=lib.getPublication_date()%></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/books/edit"
                              methods="get">
                            <input type="hidden" name="id" value="<%=lib.getId()%>">
                            <button type="submit" class="btn btn-warning btn-sm">
                                <i class="bi bi-pencil"></i>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="<%=request.getContextPath()%>/books/delete"
                              methods="post"
                              onclick="return confirm('¿Seguro que deseas borrar este libro?');">
                            <input type="hidden" name="id" value="<%=lib.getId()%>">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="bi bi-trash"></i>
                            </button>
                        </form>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
    <% } else { %>
    <div class="alert alert-warning" role="alert">
        ⚠️ No hay productos disponibles
    </div>
    <% } %>


    <!-- Botón volver -->
    <div class="mt-4">
        <a href="<%=request.getContextPath()%>" class="btn btn-secondary">
            <i class="bi bi-arrow-left"></i> Volver al inicio
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
