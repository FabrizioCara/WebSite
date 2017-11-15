<%-- 
    Document   : Login_Cliente
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
        
        <script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="js/ClienteScript.js"></script>
    </head>

    <body>
        
        <div class="page">
            
        <h1 class="titolotab"> ->WebSiteName<- </h1>
        
        <c:choose>
        
        <c:when test="${fallita == true}">
            <p class="linkp"> Operazione fallita! </p>
            <p class="linkp"> <a href="Cliente.html"> Continua </a> </p>
        </c:when>
            
        <c:when test="${Seller == true}">
            <p class="linkp"> Non sei autorizzato a visualizzare questa pagina. Effettua il login! </p>    
        </c:when>
        
        <c:when test="${Buyer == true}">  
        <div>        
            <h2> Home page </h2>
            <p> Nome: ${cliente.nomeUtente} ${cliente.cognomeUtente} </p>
            <p> Id: ${cliente.id} </p>
            <p> Credito: ${cliente.credito.saldo} </p>
            <h3> Oggetti disponibili </h3>
        </div>
            

        <div class="search">
        <label for="Filtra"> Ricerca oggetto: </label>
        <input type="text" id="Filtra" size="15"/> 
        </div>
        
                       
        <div>
        <table id="listaOggetti">
            <tr class="intest">
                <th> Nome </th>
                <th> Foto </th>
                <th> Pezzi disponibili </th>
                <th> Prezzo </th>
                <th> Link </th>
            </tr>
            
            <c:forEach var="table" items="${listaOggetti}">
                <tr class="pari">
                    <td name="oggetto"> ${table.nomeOggetto} </td>
                    <td name="oggetto"> <img title="Foto" alt="Foto ${table.nomeOggetto}" 
                              src="${table.url}" width="120" height="140"/> </td>
                    <td name="oggetto"> ${table.pezziDisponibili} </td>
                    <td name="oggetto"> ${table.prezzo} € </td>
                    <td name="oggetto"> <a href="Cliente.html?IdOggetto=${table.id}" > Link al prodotto </a> </td>
                </tr>
            </c:forEach>
        </table>
        
        <p id="paragrafo" class="linkp"></p>
        
        </div>
        </c:when>
            
        <c:when test="${riepilogo == true}">
            <div>
            <table>
                <tr class="intest"> 
                    <th> Nome </th>
                    <th> Foto </th>
                    <th> Pezzi disponibili </th>
                    <th> Prezzo </th>
                </tr>
               
                <tr class="dispari">
                    <td> ${oggetto.nomeOggetto} </td>
                    <td> <img title="Foto" src="${oggetto.url}" 
                              width="120" height="140" alt="Foto ${oggetto.nomeOggetto}"/> </td>
                    <td> ${oggetto.pezziDisponibili} </td>
                    <td> ${oggetto.prezzo} € </td>
                </tr>
            </table>
                
            <p class="linkp"> <a href="Cliente.html?Idoggetto_scelto=${oggetto.getId()}"> Conferma </a> </p>
            <p class="linkp"> <a href="Cliente.html"> Indietro </a> </p>
            </div>
        </c:when>  
            
        <c:when test="${acquistato == true}">
                <p class="linkp"> Oggetto acquistato </p>
                <p class="linkp"> <a href="Cliente.html"> Continua </a> </p>            
        </c:when>
                
        <c:when test="${Noacquistato}">
            <p class="linkp"> Attenzione! Credito insufficiente per acquistare tale oggetto. </p>
            <p class="linkp"> <a href="Cliente.html"> Continua </a> </p>
        </c:when>
                
        </c:choose>
          
        <nav>
            <ul>
                <li> <a href="descrizione.html"> Descrizione </a> </li>
                <li> <a href="Login.html"> Login </a> </li>
                <li> <a href="Login.html?logout=${1}"> Logout </a>
            </ul>
        </nav>
        </div>  
    </body>
</html>
