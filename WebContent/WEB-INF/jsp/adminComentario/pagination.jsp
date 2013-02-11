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
#customers td, #customers th {
font-size: 12px;
border: 1px solid #FF7F50;
padding: 3px 7px 2px 7px;
}
#customers th
{
font-size: 12px;
text-align: left;
padding-top: 5px;
padding-bottom: 4px;
background-color: #FFA07A;
color: #fff;
}
#customers tr.alt td
{
	color: #000;
	background-color: #EAF3D3;
}
</style>

<table id="customers">
  <thead>
     <tr>
	       <th>ID</th>
	       <th>Nota</th>
	       <th>Comentário</th>
	       <th>Usuário</th>
	       <th>Localização</th>
	       <th>Status</th>
	       <th>Remover</th>
     </tr>
  </thead>
  <tbody>
      <c:if test="${empty comentarioList}">
         <tr>
            <td colspan="7" align="center">Nenhum Comentário Encontrado!</td>
         </tr>
      </c:if>
      
      <c:if test="${not empty comentarioList}">
        <c:forEach var="com" items="${comentarioList}">
         <tr>
            <td>${com.id}</td>
            <td>${com.nota}</td>
            <td>${com.comentario}</td>
            <td>${com.usuario.nome}</td>
            <td><a href="<c:url value="/localizacao/detalhe/${com.localizacao.id}" />">${com.localizacao.nome}</a></td>
             
           <c:if test="${not com.fldAtivo}">
             <td><a href="<c:url value="/admin/comentario/aprovar/${com.id}" />"></a>Aprovar</td>
           </c:if>
           <c:if test="${com.flgAtivo}">
             <td><b>Aprovado</b></td>
           </c:if>
         
         <td><a href="<c:url value="/admin/comentario/excluir/${com.id}" />"></a>Excluir</td>
         </tr>
        </c:forEach>
      </c:if>
  </tbody>
</table>