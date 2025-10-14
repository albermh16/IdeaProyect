<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Get Params</title>
</head>
<body>
<h1><%= "Get Parameters"%>
</h1>

<br/>

<p><a href="get-params">Sin parametros</a></p>
<p><a href="get-params?saludo=caracola">LLamar al server con el param saludo</a></p>
<p><a href="get-params?saludo=Hola&nombre=melola&codigo=98">get-params?saludo=Hola&nombre=melola&codigo=98</a></p>
<p><a href="get-params?saludo=Hola&nombre=melola&codigo=mil">get-params?saludo=Hola&nombre=melola&codigo=mil</a></p>
<p><a href="get-params?saludo=Hola&nombre=melola&codigo=105">get-params?saludo=Hola&nombre=melola&codigo=105</a></p>

</body>
</html>