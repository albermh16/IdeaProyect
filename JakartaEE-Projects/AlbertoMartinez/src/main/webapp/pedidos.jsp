<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.daw.jakarta.pedidosexamen.model.Pedido" %>
<%@ page import="es.daw.jakarta.pedidosexamen.model.Cliente" %>
<%@ page import="es.daw.jakarta.pedidosexamen.utils.Utils" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Consulta de Pedidos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8fafc;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        header {
            background-color: #0d6efd;
            color: white;
            padding: 1.5rem 0;
            text-align: center;
            margin-bottom: 2rem;
        }
        table {
            background-color: white;
        }
        th {
            background-color: #0d6efd;
            color: white;
        }
        .card {
            border-radius: 1rem;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
        }
        .filter-form {
            background-color: white;
            border-radius: 1rem;
            padding: 1.5rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .status-badge {
            font-size: 0.9em;
        }
    </style>
</head>
<body>

<header>
    <h1>📦 Consulta de Pedidos</h1>
    <p class="lead mb-0">Filtra los pedidos por cliente o revisa todos los existentes</p>
</header>

<div class="container">

    <%

    %>

    <!-- === FORMULARIO DE FILTRO POR CLIENTE === -->
    <div class="filter-form mb-4">
        <form action="<%= request.getContextPath() %>/pedidos/listar" method="get" class="row g-3 align-items-center">
            <div class="col-md-6">
                <label for="clienteId" class="form-label fw-bold">Filtrar por cliente:</label>
                <select name="clienteId" id="clienteId" class="form-select">
                    <option value="<%=request.getAttribute("cliente")%>">-- Todos los clientes --</option>
                    <%
                        String clienteSeleccionado = request.getParameter("clienteId");
                        List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                        if (clientes != null) {
                            for (Cliente c : clientes) {
                                String seleccionado = (clienteSeleccionado != null && clienteSeleccionado.equals(String.valueOf(c.getId())))
                                        ? "selected" : "";
                    %>
                    <option value="<%= c.getId() %>" <%=seleccionado%>>
                        <%= c.getNombre() %> (<%= c.getNif() %>)
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-primary mt-4 w-100">Filtrar</button>
            </div>
            <div class="col-md-3">
                <a href="<%= request.getContextPath() %>/pedidos/listar" class="btn btn-outline-secondary mt-4 w-100">Mostrar todos</a>
            </div>
        </form>
    </div>

    <!-- === TABLA DE PEDIDOS === -->
    <div class="card p-4">
        <h4 class="mb-4 text-primary">📋 Lista de Pedidos</h4>
        <%
            List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
            if (pedidos == null || pedidos.isEmpty()) {

        %>
        <div class="alert alert-warning text-center">No se encontraron pedidos para los criterios seleccionados.</div>
        <%
        } else {
        %>
        <div class="table-responsive">
            <table class="table table-striped align-middle text-center">
                <thead>
                <tr>
                    <th>Cliente</th>
                    <th>Fecha y hora</th>
                    <th>Total (€)</th>

                    <th>
                        Estado
                        <a href="<%= request.getContextPath() %>/pedidos/listar?ordenEstado=asc<%
                            if (request.getParameter("clienteId") != null && !request.getParameter("clienteId").isEmpty()) {
                                out.print("&clienteId=" + request.getParameter("clienteId"));
                            }
                        %>
                        " class="sort-link" title="Orden ascendente">▲</a>

                        <a href="<%= request.getContextPath() %>/pedidos/listar?ordenEstado=desc
                        <%
                            if (request.getParameter("clienteId") != null && !request.getParameter("clienteId").isEmpty()) {
                                out.print("&clienteId=" + request.getParameter("clienteId"));
                            }
                        %>
                        " class="sort-link" title="Orden descendente">▼</a>
                    </th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Pedido p : pedidos) {
                        String nombreCliente = Utils.obtenerNombreCliente(clientes, p.getId_cliente());
                %>
                <tr>
                    <td><%= nombreCliente%></td>
                    <td><%= p.getFechaPedido() %></td>
                    <td><%= String.format("%.2f", p.getTotal()) %></td>
                    <td>
                        <% String estado = p.getEstado().name();
                            String badgeClass = switch (estado) {
                                case "ENTREGADO" -> "bg-success";
                                case "PENDIENTE" -> "bg-warning text-dark";
                                case "CANCELADO" -> "bg-danger";
                                default -> "bg-secondary";
                            };
                        %>
                        <span class="badge <%= badgeClass %> status-badge"><%= estado %></span>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <% } %>
    </div>

    <!-- BOTÓN VOLVER -->
    <div class="text-center mt-4">
        <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-outline-primary">⬅ Volver al inicio</a>
    </div>
</div>

<footer class="text-center mt-5 mb-3 text-muted">
    <p>Proyecto JAKARTAEE · Gestión de Pedidos  © 2025</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
