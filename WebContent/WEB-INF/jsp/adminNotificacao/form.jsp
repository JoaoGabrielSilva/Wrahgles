<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Notificar Clientes</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/stylesAdm.css" />" type="text/css" />
<style type="text/css"> 
#customers
{
font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
width:100%;
border-collapse:collapse;
}
#customers td, #customers th 
{
font-size:12px;
border:1px solid #FF7F50;
padding:3px 7px 2px 7px;
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

</head>
<body>

<%@ include file="../../../menuAdm.jsp" %>


<form action="<c:url value="/admin/notificacao/enviar"/>" id="frm" name="frm" method="post">

<table width="100%" align="center">
<!-- Adicionando erros -->
   <c:if test="${not empty errors}">
	   <c:forEach var="error" items="${errors}">
		    <tr>
	      		<td colspan="2" align="center"><span class="campos">${error.message}</span></td>
	    	</tr>
	   </c:forEach>
	</c:if>
  
  <tr>
    <td align="center" colspan="2" height="50px">
       <h2>Notificar Clientes</h2>
    </td>
  </tr>
  
  <tr>
     <td align="right" width="50%">
       <span class="label">Titulo:</span><span class="ast">*</span>
     </td>
     <td align="left" width="50%">
        <input type="text" name="notificacao.titulo" size="30" maxlength="200" value="${notificacao.titulo}"/>
     </td>
  </tr>
  
  <tr>
    <td align="right" valign="top">
      <span class="label">Mensagem:</span><span class="ast">*</span>
    </td>
    <td align="left">
     <textarea rows="10" cols="39" name="notificacao.mensagem" id="notificacao.mensagem">${notificacao.mensagem}</textarea>
    </td>
  </tr>
  
  <tr>
    <td align="right">
       <input type="checkbox" name="notificacao.ativo" />
    </td>
    <td align="left">
       <span class="label">Enviar email para os Clientes</span>
    </td>
  </tr>
  
  <tr>
     <td></td>
     <td align="left" colspan="2" style="width: 160px">
        <input type="submit" value="Enviar email"> 
     </td>
  </tr>
</table>
</form>
<%@ include file="../../../footerAdm.jsp" %>
