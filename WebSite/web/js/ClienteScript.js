/* 
    Document   : ClienteScripting
    Created on : Maggio 2016
    Author     : Fabrizio Cara
*/

$(document).ready(function(){
    
    $("#Filtra").keyup(function() {
        
        //recupera il valore di Filtra
        var text= $("#Filtra").val(); 
        
        $.ajax({
            url : "filter.json",
            data:{
                key: "search",
                q: text
            },
            dataType: "json",
        
            success: function(data, state){
                aggiornaListaOggetti(data, state);
            },
            error: function(data, state){
                print("errore");
            }
        });

        
        function aggiornaListaOggetti(listaObj){
            
            if (listaObj.length !== 0){
                $("#listaOggetti").empty();
            
                var newtrt = document.createElement("tr");
                var newtd1t = document.createElement("th");
                var newtd2t = document.createElement("th");
                var newtd3t = document.createElement("th");
                var newtd4t = document.createElement("th");
                var newtd5t = document.createElement("th");
            
                newtrt.setAttribute("class", "intest");
                        
                var td1t = document.createTextNode("Nome");
                newtd1t.appendChild(td1t);
            
                var td2t = document.createTextNode("Foto");
                newtd2t.appendChild(td2t);
                
                var td3t = document.createTextNode("Pezzi disponibili");
                newtd3t.appendChild(td3t);
                
                var td4t = document.createTextNode("Prezzo");
                newtd4t.appendChild(td4t);
                
                var td5t = document.createTextNode("Link");
                newtd5t.appendChild(td5t);
            
                newtrt.appendChild(newtd1t);
                newtrt.appendChild(newtd2t);
                newtrt.appendChild(newtd3t);
                newtrt.appendChild(newtd4t);
                newtrt.appendChild(newtd5t);
            
                $("#listaOggetti").append(newtrt);
            
                for(var oggetto in listaObj){
                
                    var newtr = document.createElement("tr");
                    var newtd1 = document.createElement("td");
                    var newtd2 = document.createElement("td");
                    var newtd3 = document.createElement("td");
                    var newtd4 = document.createElement("td");
                    var newtd5 = document.createElement("td");
                
                    newtr.setAttribute("class", "pari");
                    
                                
                    var td1 = document.createTextNode(listaObj[oggetto].nome);
                    newtd1.appendChild(td1);
                
                    var td2 = document.createElement("img");
                    td2.setAttribute("title", "Foto");
                    td2.setAttribute("alt", "Foto " + listaObj[oggetto].nome);
                    td2.setAttribute("width", "120");
                    td2.setAttribute("height", "140");
                    td2.setAttribute("src", listaObj[oggetto].url);
                    newtd2.appendChild(td2);
                
                    var td3 = document.createTextNode(listaObj[oggetto].pezzi);
                    newtd3.appendChild(td3);
                
                    var td4 = document.createTextNode(listaObj[oggetto].prezzo + " €");
                    newtd4.appendChild(td4);
                
                    var link = document.createElement("a");
                    link.setAttribute("href", "Cliente.html?IdOggetto=" + listaObj[oggetto].id);
                    var registraTxt = document.createTextNode("Link al prodotto");
                    link.appendChild(registraTxt);
                    newtd5.appendChild(link);
                
                    newtr.appendChild(newtd1);
                    newtr.appendChild(newtd2);
                    newtr.appendChild(newtd3);
                    newtr.appendChild(newtd4);
                    newtr.appendChild(newtd5);
                
                    $("#listaOggetti").append(newtr); 
                }
            }
            
            else {
                $("#listaOggetti").empty();
                $("#paragrafo").empty();
                
                var ad = document.createTextNode("Nessun oggetto trovato");
               
                $("#paragrafo").append(ad);
            }
        }
    });
});
