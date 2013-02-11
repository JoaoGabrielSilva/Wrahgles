<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
#customers
{
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
text-align:left;
padding-top:5px;
padding-bottom:4px;
background-color:#FFA07A;
color:#fff;
}
#customers tr.alt td 
{
color:#000;
background-color:#EAF2D3;
}
</style>

<table id="customers">
  <thead>
    <tr>
     <th>ID</th>
     <th>Nome</th>
     <th>Endereço</th>
     <th>Site</th>
     <th>Telefone</th>
     <th>Cadastrado</th>
     <th>Detalhes</th>
    </tr>
  </thead>
  <tbody>
    <c:if test="${empty localizacaoList }">
      <tr>
          <td colspan="7" align="center">Nenhuma Localização Encontrada!</td>
      </tr>
    </c:if>
    <c:if test="${not empty localizacaoList }">
     <c:forEach var="local" items="${localizacaoList}">
      <tr>
         <td>${local.id}</td>
         <td><a href="<c:url value="/admin/localizacao/alterar/${local.id}"/>">${local.nome}</a></td>
         <td>${local.endereco}</td>
         <td><a href="${local.site}" target="_blank">${local.site}</a></td>
         <td>${local.telefone}</td>
         <td>${local.usuarioInsert.nome}</td>
         <td><a href="<c:url value="/localizacao/detalhe/${local.id}" />">Visualizar</a></td>
       </tr>
    </c:forEach>
    </c:if>
  
  </tbody>

</table>

