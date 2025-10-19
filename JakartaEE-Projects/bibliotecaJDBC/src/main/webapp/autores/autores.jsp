<%@ page import="com.github.alberto.bibliotecajdbc.model.Autor" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Autores</title>
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
        .card {
            border: none;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
    </style>
</head>
<body>
<%
    List<Autor> autores = (List<Autor>) request.getAttribute("autores");
%>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark px-3">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/welcome.jsp">Biblioteca</a>
</nav>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="fw-bold text-dark">Autores registrados</h3>
        <a href="<%=request.getContextPath()%>/authors/new" class="btn btn-success">
            <i class="bi bi-plus-lg"></i> Nuevo autor
        </a>
    </div>

    <div class="card p-3">
        <table class="table table-striped table-hover align-middle">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Editar</th>
                <th>Borrar</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(Autor aut : autores){

            %>
            <tr>
                <td><%=aut.getId()%></td>
                <td><%=aut.getNombre()%></td>
                <td>
                    <form action="<%=request.getContextPath()%>/authors/edit"
                          method="get">
                        <input type="hidden" name="id" value="<%=aut.getId()%>">
                        <button type="submit" class="btn btn-warning btn-sm">
                            <i class="bi bi-pencil"></i>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="<%=request.getContextPath()%>/authors/delete"
                          method="post"
                          onclick="return confirm('¿Seguro que deseas borrar este libro?');">
                        <input type="hidden" name="id" value="<%=aut.getId()%>">
                        <button type="submit" class="btn btn-danger btn-sm">
                            <i class="bi bi-trash"></i>
                        </button>
                    </form>
                </td>
            </tr>
            </tbody>
            <% } %>


        </table>
    </div>

    <div class="mt-4">
        <a href="<%=request.getContextPath()%>/welcome.jsp" class="btn btn-secondary">
            <i class="bi bi-arrow-left"></i> Volver al inicio
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
