<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestión de Biblioteca</title>
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
        .card {
            border: none;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            transition: transform 0.2s;
        }
        .card:hover {
            transform: scale(1.03);
        }
        .btn-primary {
            background-color: #0d6efd;
            border: none;
        }
    </style>
</head>

<%--

    if (lang == null) lang = "es";
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
--%>
<%
    String color = (String)request.getAttribute("color");
    String usuario = (String)request.getAttribute("usuario");
%>
<body style="background-color: <%=color%>">




<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark px-3">
    <a class="navbar-brand" href="/welcome.jsp">Biblioteca</a>
    <div class="ms-auto">
        <div class="dropdown">
            <button class="btn btn-outline-light dropdown-toggle" type="button" data-bs-toggle="dropdown">
                Preferencia de idioma
            </button>
            <ul class="dropdown-menu dropdown-menu-end">
                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/es">Español</a></li>
                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/en">Inglés</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Contenido principal -->
<div class="container text-center mt-5">
    <h2 class="fw-bold"></h2>
    <p class="text-muted">Administra tus libros y autores fácilmente</p>
    <h2>Bienvenido, <%= usuario %> </h2>
    <a href="<%=request.getContextPath()%>/logout" class="btn btn-danger">Cerrar sesión</a>
    <div class="row justify-content-center mt-4">
        <div class="col-md-3">
            <div class="card p-4">
                <div class="mb-3">
                    <i class="bi bi-book" style="font-size: 2rem;"></i>
                </div>
                <h5>Libros</h5>
                <a href="books/list" class="btn btn-primary mt-3">Gestionar libros</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card p-4">
                <div class="mb-3">
                    <i class="bi bi-person" style="font-size: 2rem;"></i>
                </div>
                <h5>Autores</h5>
                <a href="authors/list" class="btn btn-primary mt-3">Gestionar autores</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
