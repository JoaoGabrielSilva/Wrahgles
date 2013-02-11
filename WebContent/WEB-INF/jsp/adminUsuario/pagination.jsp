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
#customers th{

font-size: 12px;
text-align: left;
padding-top: 5px;
padding-bottom: 4px;
background-color: #FFA07A;
color: #fff;
}

#customers tr.alr td{
color: #000;
background-color: #EAF2D3; 
}
</style>

<table id="customers">
  <thead>
  <tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Email</th>
    <th>Data Cadastro</th>
    <th>Última Atualização</th>
    <th>Último Acesso</th>
    <th>Qtd Acesso</th>
    <th>Permissão</th>
  </tr>
  </thead>
  <tbody>
     <c:if test="${empty usuarioList}">
       <tr>
          <td colspan="8" align="center">Nenhum Usuário Encontrado!</td>
       </tr>
     </c:if>
     <c:if test="${not empty usuarioList}">
     <c:forEach var="usuario" items="${usuarioList}">
      <tr>
        <td>${usuario.id}</td>
        <td>${usuario.nome}</td>
        <td>${usuario.email}</td>
        <td>${usuario.dtCadastroFormat}</td>
        <td>${usuario.dtUltimoUpdateFormat}</td>
        <td>${usuario.dtUltimoAcessoFormat}</td>
        <td>${usuario.qtdAcesso}</td>
        <td>${usuario.role}</td>
      </tr>
     </c:forEach>
     </c:if>
  </tbody>
</table>




