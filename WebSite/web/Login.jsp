<%-- 
    Document   : Login
    Created on : Aprile 2016
    Author     : fabri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabrizio Cara">
        <meta name="keywords" content="">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>

    <body>
        
        <div class="page">
            
        <h1> ->WebSiteName<- </h1>
                
        <div>
            <h2 class="titoloform"> Login </h2>
        </div>
        
        <div>
            <c:if test="${errore != null}">
                <p class="titoloform">${errore}</p>
            </c:if>
                
        <form method="post" action="Login.html">
            <div>
            <label for="User"> Username: </label>
            <input type="text" name="Username" id="User" />
            <label for="Passw"> Password: </label>
            <input type="password" name="Password" id="Passw" />
            </div>
            
            <div class="submit">
            <input type="submit" value="Accedi" name="Submit" />
            </div>
        </form>
        </div>
        
        <nav>
            <ul>
                <li> <a href="descrizione.html"> Descrizione </a> </li>
                <li> <a href="Cliente.html"> Cliente </a> </li>
                <li> <a href="Venditore.html"> Venditore </a> </li>
            </ul>
        </nav>
        </div>  

    </body>
</html>
