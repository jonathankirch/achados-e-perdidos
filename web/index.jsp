<%-- 
    Document   : index
    Created on : Sep 22, 2026, 8:15:31 PM
    Author     : jonat
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Achados e Perdidos</title>

        <%-- Bootstrap 5 --%>
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <%-- Icones do Material Design --%>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <%-- Scripts --%>
        <script src="js/jquery-3.7.1.min.js"></script>
        <script defer src="js/bootstrap.bundle.min.js"></script>
    </head>
    <body class="bg-light">
        <%@include file="menu.jsp" %>

        <div class="my-5">
            <h1 class="font-weight-bold text-center">Achados e Perdidos</h1>
            <p class="text-muted text-center mb-4">
                Consulte abaixo a lista de objetos perdidos e encontrados recentemente.
            </p>
        </div>

        <div class="container my-3">
            <%@include file="tabela.jsp" %>
        </div>
    </body>
</html>
