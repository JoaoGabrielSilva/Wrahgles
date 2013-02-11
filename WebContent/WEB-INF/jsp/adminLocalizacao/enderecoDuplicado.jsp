<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Administrador</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/stylesAdm.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/pagination.css" />" type="text/css" />

<style type="text/css">
#customers{
font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
width: 100%;
border-collapse: collapse;
}
#customers td, #customers th
{
font-size: 12px;
border: 1px solid #FF7F50;
padding: 3px 7px 2px 7px;
}
#customers th
{
font-size:12px;
text-align: left;
padding-top: 5px;
padding-bottom: 4px;
background-color: #FFA07A;
color: #fff; 
}
#customers tr.alt td
{
color: #000;
background-color: #EAF2D3;
}
</style>


</head>

<body>

<%@ include file="../../../menuAdm.jsp" %>


<table id="customers">
  <thead>
    <tr>
       <th>Quantidade de Locais</th>
       <th>Latitude</th>
       <th>Longitude</th>
       <th>Detalhes</th>
    </tr>
  </thead>
  <tbody>
     <c:if test="${empty groupLocal}">
      <tr>
         <td colspan="7" align="center">Nenhuma Localização no mesmo Endereço Encontrada!</td>
      </tr>
     </c:if>
     <c:if test="${not empty groupLocal}">
      <c:forEach var="local" items="${groupLocal}">
      <tr>
         <td>${local.qtdLocal}</td>
         <td>${local.latitude}</td>
         <td>${local.longitude}</td>
         <td><a href="">Exibir Lista</a></td>      
      </tr>
      </c:forEach>
     </c:if>
  </tbody>

</table>
<%@ include file="../../../footerAdm.jsp"%>