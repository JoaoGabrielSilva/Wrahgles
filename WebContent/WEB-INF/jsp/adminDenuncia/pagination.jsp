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
     <th>Comentário</th>
     <th>Data</th>
     <th>Usuário</th>
     <th>Localização</th>
   </tr>
  </thead>
  <tbody>
  <c:if test="${empty denunciaList}">
    <tr>
        <td colspan="6" align="center">Nenhuma Denúncia Encontrada!</td>
    </tr>
  </c:if>
  <c:if test="${not empty denunciaList}">
   <c:forEach var="denuncia" items="${denunciaList}">
     <tr>
        <td>${denuncia.id}</td>
        <td>${denuncia.comentario}</td>
        <td>${denuncia.dtDenuncia}</td>
        <td>${denuncia.usuario.nome}</td>
        <td><a href="<c:url value="/admin/localizacao/alterar/${denuncia.localizacao.id }"/>">${denuncia.localizacao.nome}</a></td>
     </tr>
   </c:forEach>
  </c:if>
 
  </tbody>
</table>