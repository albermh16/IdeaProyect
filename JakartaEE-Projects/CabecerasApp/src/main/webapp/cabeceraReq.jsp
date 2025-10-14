<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Información de la petición</title>
</head>
<body>
<h2>Datos de la petición</h2>
<ul>
    <li><strong>Método HTTP:</strong> ${metodoHttp}</li>
    <li><strong>Request URI:</strong> ${requestUri}</li>
    <li><strong>Request URL:</strong> ${requestUrl}</li>
    <li><strong>Context Path:</strong> ${contextPath}</li>
    <li><strong>Servlet Path:</strong> ${servletPath}</li>
    <li><strong>IP Cliente:</strong> ${ipCliente}</li>
    <li><strong>IP Local:</strong> ${ipLocal}</li>
    <li><strong>Puerto Local:</strong> ${port}</li>
    <li><strong>Scheme:</strong> ${scheme}</li>
    <li><strong>Host:</strong> ${host}</li>
    <li><strong>URL construida:</strong> ${url}</li>
    <li><strong>URL construida 2:</strong> ${url2}</li>
</ul>
</body>
</html>