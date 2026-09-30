<%-- 
    Document   : contato
    Created on : Sep 25, 2026, 8:59:25 PM
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Contato</title>

        <%-- Bootstrap 5 --%>
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <%-- Icones do Material Design --%>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <%-- Scripts --%>
        <script src="js/jquery-3.7.1.min.js"></script>
        <script defer src="js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <%@include file="menu.jsp" %>
        <div class="my-5 w-75 mx-auto">
            <form id="contact-form" class="w-50 m-auto">
                <h2>Entre em contato</h2>

                <!-- Name input -->
                <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" id="name" name="name" class="form-control" />
                    <label class="form-label" for="name">Nome</label>
                </div>

                <!-- Email input -->
                <div data-mdb-input-init class="form-outline mb-4">
                    <input type="email" id="email" name="email" class="form-control" />
                    <label class="form-label" for="email">Email</label>
                </div>

                <!-- Subject input -->
                <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" id="subject" name="subject" class="form-control" />
                    <label class="form-label" for="subject">Motivo</label>
                </div>

                <!-- Message input -->
                <div data-mdb-input-init class="form-outline mb-4">
                    <textarea class="form-control" id="message" name="message" rows="4"></textarea>
                    <label class="form-label" for="message">Mensagem</label>
                </div>

                <!-- Submit button -->
                <button data-mdb-button-init id="submit-form" type="submit" data-mdb-ripple-init class="btn btn-primary btn-block mb-4">
                    Enviar
                </button>
            </form>
        </div>
    </body>
</html>
