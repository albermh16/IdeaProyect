<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f7fa;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            font-family: 'Segoe UI', sans-serif;
        }
        .login-card {
            background: #fff;
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 100%;
            max-width: 420px;
        }
        .login-title {
            font-weight: 700;
            color: #1a1d23;
            text-align: center;
        }
        .btn-login {
            background-color: #0d6efd;
            border: none;
            transition: all 0.2s ease;
        }
        .btn-login:hover {
            background-color: #0b5ed7;
        }
        .form-control, .form-control-color {
            border-radius: 8px;
        }
    </style>
</head>
<body>

<%
    String error = (String) request.getAttribute("errorMessage");
    String success = (String) request.getAttribute("successMessage");
%>

<div class="login-card">
    <h3 class="login-title mb-4">Iniciar Sesión</h3>

    <% if (error != null) { %>
    <div class="alert alert-danger text-center"><%= error %></div>
    <% } %>

    <% if (success != null) { %>
    <div class="alert alert-success text-center"><%= success %></div>
    <% } %>

    <form action="<%=request.getContextPath()%>/login" method="post">

        <!-- Usuario -->
        <div class="mb-3">
            <label for="username" class="form-label fw-semibold">Usuario</label>
            <div class="input-group">
                <span class="input-group-text"><i class="bi bi-person"></i></span>
                <input type="text" class="form-control" id="username" name="username"
                       placeholder="Introduce tu usuario" required
                       value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
            </div>
        </div>

        <!-- Contraseña -->
        <div class="mb-3">
            <label for="password" class="form-label fw-semibold">Contraseña</label>
            <div class="input-group">
                <span class="input-group-text"><i class="bi bi-lock"></i></span>
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="Introduce tu contraseña" required>
            </div>
        </div>

        <!-- 🎨 Color favorito -->
        <div class="mb-3">
            <label for="color" class="form-label fw-semibold">Color favorito</label>
            <div class="input-group">
                <span class="input-group-text"><i class="bi bi-palette"></i></span>
                <input type="color" class="form-control form-control-color" id="color" name="color"
                       value="<%= request.getParameter("color") != null ? request.getParameter("color") : "#0d6efd" %>"
                       title="Elige tu color favorito">
            </div>
            <small class="text-muted">Selecciona tu color preferido.</small>
        </div>

        <!-- Botón -->
        <div class="d-grid mt-4">
            <button type="submit" class="btn btn-login text-white fw-semibold">
                <i class="bi bi-box-arrow-in-right me-2"></i>Entrar
            </button>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
