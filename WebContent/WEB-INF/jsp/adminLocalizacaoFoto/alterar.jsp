<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Administrador</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/stylesAdm.css" />" type="text/css" />

</head>
<body>

<%@ include file="../../../menuAdm.jsp" %>

<form action="<c:url value="/admin/localizacaoFoto/salvar" />" method="post">

<input type="hidden" name="localizacaoFoto.id" value="${localFoto.id}">

<table width="100%">
  <tr>
	   <td>
		 	<h2>Local: ${localFoto.localizacao.nome}</h2>
	   </td>
  </tr>
  
  <tr>
    <td align="left" colspan="2">
       <h2>Usuário: ${localFoto.usuario.nome}</h2>
    </td>
  </tr>
  
  <tr>
    <td align="left" colspan="2">
       <h2>Data: ${localFoto.dataFormat}</h2>
    </td>
  </tr>
  
  <tr>
     <td align="left" colspan="2">
         <img alt="" src="<c:url value="/${localFoto.imagem}"/>">
     </td>
  </tr>
  
  <tr>
      <td align="right" width="20px">
         Status:
      </td>
      <td align="left"> 
       <select name="localizacaoFoto.flgAtivo">
          <c:if test="${localFoto.flgAtivo}">
            <option value="true" selected="selected">Aprovada</option>
            <option value="false">Pendente</option>
          </c:if>
          <c:if test="${not localFoto.flgAtivo}">
             <option value="true">Aprovada</option>
             <option value="false" selected="selected">Pendente</option>
          </c:if>
       </select>
      </td>
   </tr>
   
   <tr>
      <td align="left" colspan="2"><input type="submit" value="Salvar"></td>
   </tr>

</table>
</form>

<%@ include file="../../../footerAdm.jsp" %>

