<%-- 
    Document   : listaOggettiJson
    Created on : 1-giu-2016, 17.56.22
    Author     : fabrizio
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="oggetto" items="${listaObj}">
        <json:object>
            <json:property name="nome" value="${oggetto.nomeOggetto}"/>
            <json:property name="prezzo" value="${oggetto.prezzo}"/>
            <json:property name="id" value="${oggetto.id}"/>
            <json:property name="pezzi" value="${oggetto.pezziDisponibili}"/>
            <json:property name="url" value="${oggetto.url}"/>
        </json:object>
    </c:forEach>
</json:array>