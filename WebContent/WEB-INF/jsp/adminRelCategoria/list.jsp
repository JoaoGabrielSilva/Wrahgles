<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - add SubCategoria</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/stylesAdm.css" />" type="text/css" />

<!-- AUTOCOMPLETE -->
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-latest.js" />"></script>
<link rel="stylesheet" href="<c:url value="/css/autocomplete/jquery.autocomplete.css" />" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery/external/jquery.bgiframe-2.1.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/autocomplete/jquery.autocomplete.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/utils.js" />"></script>

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
</head>
<body>

<%@ include file="../../../menuAdm.jsp" %>

<form name="frm" id="frm" action="<c:url value="/admin/relCategoria/salvar/${relCategoria.categoria.id}" />" method="post">

<table width="100%" align="center">

   <!-- Adicionando erros -->
  <c:if test="${not empty errors }">
    <c:forEach items="${errors}" var="error">
       <tr>
            <td colspan="2" align="center"><span class="campo">${error.message}</span></td>
       </tr>
    </c:forEach>
  </c:if>
  
  <tr>
     <td align="center" colspan="2" height="50px">
        <h2>Adicionar SubCategoria a Categoria (${relCategoria.categoria.nome})</h2>
     </td>
  </tr>
  <tr> 
     <td  align="right" width="50%">
       <span class="label">SubCategoria</span><span class="ast">*</span>
     </td>
     <td align="left" width="50%"> 
         <input type="text" id="subcategoria"  name="relCategoria.subCategoria.nome" size="30" maxlength="200" value="${relCategoria.subCategoria.nome}">
     </td>
   </tr>
   
   <tr>
   	 <td align="right" width="50%" valign="top"><span class="label">Descrição:</span><span class="ast">*</span> </td>
    	<td align="left" width="50%" valign="bottom">
       	<textarea name="relCategoria.descricao" rows="7" cols="25" onkeyup="javascript:blocTexto(this,250, document.frm.contador)">${relCategoria.descricao }</textarea>
        <input type="text" name="contador" id="contador" size="4" value="250" disabled="disabled">
   	 </td>
   </tr>
   
   <tr>
       <td align="center" colspan="2">
           <input type="submit" value="Salvar">
      </td>
   </tr>
 </table>
</form>

<script type="text/javascript">
$(document).ready(function(){
	var contexto = "";
	if(window.location.pathname.search("Wrahgles") != -1){
		contexto = "/Wrahgles";
	}
	
	$("#subcategoria").autocomplete(contexto + "/admin/subcategoria/listJSON", {
		dataType: "json", // pra falar que vamos tratar um json
		parse: function(json) { // para tratar o json a função map vai iterar por toda a lista, e transformar os dados usando a função passada
			return $.map(json.list, 
				function(subCategoria) {
					return {
						data: subCategoria, // todos os dados do produto
						value: subCategoria.nome, // o valor lógico do produto
						result: subCategoria.nome // o que vai aparecer ao selecionar
					};
				});
		},

		formatItem: function(subCategoria) { // o que vai aparecer na lista de autocomplete
			return subCategoria.nome;
		}
	});
});

</script>

<%@ include file="../../../footerAdm.jsp" %>

