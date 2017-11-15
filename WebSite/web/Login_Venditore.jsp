<%-- 
    Document   : Login_Venditore
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
        
        <c:choose>
            
        <c:when test="${noCompilato == true && Seller == true}">
            <p class="linkp"> Per inserire un oggetto devi compilare tutti i campi. </p>
            <p class="linkp"> <a href="Login.html"> Continua </a> </p>
        </c:when>
            
        <c:when test="${noCompilato == true && Update == true}">
            <p class="linkp"> Per modificare un oggetto devi compilare tutti i campi. </p>
            <p class="linkp"> <a href="Login.html?Valore=${1}"> Continua </a> </p>
        </c:when>
            
        <c:when test="${Buyer == true}">
            <p class="linkp"> Non sei autorizzato a visualizzare questa pagina. Effettua il login! </p>    
        </c:when>
        
        <c:when test="${Seller == true && Update == false}">  
        <div>     
            <h2 class="titoloform"> Home page </h2>
            <p class="titoloform, p" > Nome: ${venditore.nomeUtente} ${venditore.cognomeUtente} </p>
            <p class="titoloform, p"> Id: ${venditore.id} </p>
            <p class="titoloform, p"> Credito: ${venditore.credito.saldo} </p>
        </div> 
        
        <div>
        <h2 class="titoloform"> Inserimento oggetto </h2>
        </div>
       
        <div>
        <form action="Venditore.html" method="post">
            <div>
            <label for="nome"> Nome oggetto: </label>
            <input type="text" name="NomeOggetto" id="nome" />
            
            <label for="immagine"> URL immagine: </label>
            <input type="url" name="Url" id="immagine" />
            
            <label for="descrizione"> Descrizione: </label>
            <textarea rows="6" cols="25" maxlength="200" name="Descrizione" id="descrizione" > </textarea>
            
            <label for="prezzo"> Prezzo (€): </label>
            <input type="number" name="Prezzo" id="prezzo" min="0" />
            
            <label for="quantita"> Pezzi disponibili: </label>
            <input type="number" name="Quantita" id="quantita" min="0" />
            </div>
            
            <div class="submit1">
            <input type="submit" value="Aggiungi prodotti" name="SubmitVenditore"/>
            </div>
        </form>
        </div>
        
        <div>
        <h2 class="titoloform"> Modifica oggetto </h2>
        </div>
        
        <div>
            <form action="Venditore.html">
                <select name="ID">
                    <c:forEach var="item" items="${listaOggettiVendesi}">                        
                    <option value="${item.id}"> Nome: ${item.nomeOggetto} - Prezzo: ${item.prezzo} - Pezzi: ${item.pezziDisponibili}</option>
                    </c:forEach> 
                </select>
                <div class="submit2">
                    <input type="submit" name="OggettoMod" value="Modifica">
                </div>
                <div class="submit3">
                    <input type="submit" name="OggettoCanc" value="Cancella">
                </div>
            </form>
        </div>            
        </c:when>
        
        <c:when test="${Seller == true && Update == true}">  
        <div>     
            <h2 class="titoloform"> Pagina di modifica </h2>
            <p class="titoloform, p"> Nome: ${venditore.nomeUtente} ${venditore.cognomeUtente} </p>
            <p class="titoloform, p"> Id: ${venditore.id} </p>
            <p class="titoloform, p"> Credito: ${venditore.credito.saldo} </p>
        </div> 
        
        <div>
        <h2 class="titoloform"> Modifica oggetto </h2>
        </div>
       
        <div>
        <form action="Venditore.html" method="post">
            <div>
            <label for="nome"> Nome oggetto: </label>
            <input type="text" name="NomeOggetto" id="nome" />
            
            <label for="immagine"> URL immagine: </label>
            <input type="url" name="Url" id="immagine" />
            
            <label for="descrizione"> Descrizione: </label>
            <textarea rows="6" cols="25" maxlength="200" name="Descrizione" id="descrizione" > </textarea>
            
            <label for="prezzo"> Prezzo (€): </label>
            <input type="number" name="Prezzo" id="prezzo" min="0" />
            
            <label for="quantita"> Pezzi disponibili: </label>
            <input type="number" name="Quantita" id="quantita" min="0" />
            </div>
            
            <div class="submit1">
            <input type="submit" value="Modifica oggetto" name="UpdateItem"/>
            </div>
        </form>
        
        
        
        </div>
        </c:when>
            
        <c:when test="${riepilogo == true && Seller == true}">
            <div>
            <h3 class="linkp"> Riepilogo Inserimento </h3>
            <table>  
                <tr class="intest">
                    <th> Nome </th>
                    <th> Foto </th>
                    <th> Descrizione </th>
                    <th> Pezzi disponibili </th>
                    <th> Prezzo </th>
                </tr>
                
                <tr class="dispari">
                    <td> ${oggettoAggiunto.nomeOggetto} </td>
                    <td> <img title="Foto" src="${oggettoAggiunto.url}" 
                              width="120" height="140" alt="Foto ${oggettoAggiunto.nomeOggetto}"/> </td>
                    <td class="Descrizione"> ${oggettoAggiunto.descrizione} </td>
                    <td> ${oggettoAggiunto.pezziDisponibili} </td>
                    <td> ${oggettoAggiunto.prezzo} € </td>
                </tr>
            </table>
                
            <p class="linkp"> <a href="Login.html"> Inserisci un altro oggetto </a> </p>
                
            </div>
        </c:when>
            
        <c:when test="${riepilogo == true && Update == true}">
            <div>
            <h3 class="linkp"> Riepilogo Modifica </h3>
            <table>  
                <tr class="intest">
                    <th> Nome </th>
                    <th> Foto </th>
                    <th> Descrizione </th>
                    <th> Pezzi disponibili </th>
                    <th> Prezzo </th>
                </tr>
                
                <tr class="dispari">
                    <td> ${oggettoAggiunto.nomeOggetto} </td>
                    <td> <img title="Foto" src="${oggettoAggiunto.url}" 
                              width="120" height="140" alt="Foto ${oggettoAggiunto.nomeOggetto}"/> </td>
                    <td class="Descrizione"> ${oggettoAggiunto.descrizione} </td>
                    <td> ${oggettoAggiunto.pezziDisponibili} </td>
                    <td> ${oggettoAggiunto.prezzo} € </td>
                </tr>
            </table>
                
            <p class="linkp"> <a href="Login.html"> Torna alla Home page </a> </p>
                
            </div>
        </c:when>
            
        <c:when test="${riepilogo == true && Delete == true}">
            <div>
            <h3 class="linkp"> Riepilogo Cancellazione </h3>
            <table>  
                <tr class="intest">
                    <th> Nome </th>
                    <th> Foto </th>
                    <th> Descrizione </th>
                    <th> Pezzi disponibili </th>
                    <th> Prezzo </th>
                </tr>
                
                <tr class="dispari">
                    <td> ${oggettoAggiunto.nomeOggetto} </td>
                    <td> <img title="Foto" src="${oggettoAggiunto.url}" 
                              width="120" height="140" alt="Foto ${oggettoAggiunto.nomeOggetto}"/> </td>
                    <td class="Descrizione"> ${oggettoAggiunto.descrizione} </td>
                    <td> ${oggettoAggiunto.pezziDisponibili} </td>
                    <td> ${oggettoAggiunto.prezzo} € </td>
                </tr>
            </table>
                
            <p class="linkp"> <a href="Login.html"> Torna alla Home page </a> </p>
                
            </div>
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
