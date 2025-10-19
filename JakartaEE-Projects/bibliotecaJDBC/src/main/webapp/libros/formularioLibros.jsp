<%@ page import="com.github.alberto.bibliotecajdbc.model.Autor" %>
<%@ page import="java.util.List" %>
<%@ page import="com.github.alberto.bibliotecajdbc.model.Libro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Libro</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<%
    String error = (String) session.getAttribute("errorMessageForm");
    String success = (String) session.getAttribute("successMessageForm");

    if (error != null) {
%>
<div class="alert alert-danger text-center"><%= error %></div>
<%
        session.removeAttribute("errorMessageForm");
    }
%>



<%
    List<Autor> autores = (List<Autor>) request.getAttribute("autores");
    Libro libro = (Libro) request.getAttribute("libro");

    boolean edicion = libro != null;

    String titulo = edicion ? "Editar libro":"Nuevo libro";
    String accion = edicion ? "edit":"new";
%>

<div class="container my-5">
    <div class="card shadow-sm mx-auto" style="max-width: 700px; border-radius: 15px;">
        <div class="card-body">
            <h2 class="text-center mb-4 fw-bold" style="color:#007bff;"><%=titulo%></h2>

            <form action="<%=request.getContextPath()%>/books/<%=accion%>" method="post">

                <!-- ID del libro -->
                <div class="mb-3">
                    <label for="id" class="form-label fw-semibold">ID del libro</label>
                    <input type="text" class="form-control" id="id" name="id" placeholder="Ej: 1"
                    value="<%=edicion ? libro.getId() : ""%>" <%=edicion ? "readonly" : ""%>>
                    <small class="text-muted">El ID identifica de forma única al libro en la base de datos.
                        <% if (edicion) { %>
                        (No se puede modificar)
                        <% } %></small>
                </div>

                <!-- Título -->
                <div class="mb-3">
                    <label for="title" class="form-label fw-semibold">Título del libro</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="Ej: Cien años de soledad"
                    value="<%=edicion ? libro.getTitle() : ""%>">
                </div>

                <!-- Fecha de publicación -->
                <div class="mb-3">
                    <label for="publication_date" class="form-label fw-semibold">Fecha de publicación</label>
                    <input type="date" class="form-control" id="publication_date" name="publication_date"
                    value="<%=edicion ? libro.getPublication_date() : ""%>">
                </div>

                <!-- Autor -->
                <div class="mb-3">
                    <label for="author" class="form-label fw-semibold">Autor</label>
                    <select class="form-select" id="author" name="author_id">
                        <option value="">-- Selecciona un autor --</option>
                        <%
                            if(autores != null && !autores.isEmpty()){
                                for(Autor aut :autores){
                                    boolean seleccionado = false;
                                    if(edicion)
                                        seleccionado = aut.getId().equals(libro.getAuthor_id());
                        %>
                        <option value="<%=aut.getId()%>"<%=seleccionado ? "selected" : ""%>><%=aut.getNombre()%></option>
                        <%
                                    }
                            }else{
                        %>
                        <option disabled>No hay autores disponibles</option>
                        <% } %>
                    </select>
                </div>

                <!-- Botones -->
                <div class="d-flex justify-content-between mt-4">
                    <a href="${pageContext.request.contextPath}/books/list" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-circle"></i> Cancelar
                    </a>
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-save"></i> Guardar libro
                    </button>
                </div>

            </form>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
