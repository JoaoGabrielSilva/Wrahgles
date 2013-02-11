<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Contato</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="url" content="http://www.Wrahgles.com.br/contato/form" />
<meta name="title" content="Contato" />
<meta name="keys" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="keywords" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="description" content="O Wrahgles ajuda você a encontrar os locais e serviços que estão próximos de você. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="abstract" content="O Wrahgles ajuda você a encontrar os locais e serviços que estão próximos de você. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />

<meta name="google-site-verification" content="QeV-cZPQkppinYbnmmZUY6R3zn7foOYxMPykuCN9jfU" />

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />

<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.maskedinput-1.2.2.js" />" ></script>
<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />
<script type="text/javascript">
jQuery(function($){
	$("#telefone").mask("(99) 99999-9999");
});
<script type="text/javascript">

var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-35303494-1']);
_gaq.push(['_trackPageview']);

(function() {
  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();

</script>
</script>

</head>
<body>

<%@ include file="../../../menu.jsp"  %>

<div id="content">
   <div id="position-contato">
      <div id="wrahgles-contato">
        
        <!-- adicionando erros -->
         <c:if test="${not empty errors  }">
	         <c:forEach items="${errors }" var="error">
	            <p><span class="campos">(<span id="defined">*</span>) ${errors.message}</span></p>
	         </c:forEach>
	     </c:if>
         
         <h3><fmt:message key="preencha.contato.form"/></h3>
         <p><fmt:message key="campos.com.contato"/> <span id="defined">*</span> <fmt:message key="campo.obrigatorio.contato"/></p>
        
        <form name="contato" id="contato" action="<c:url value="/contato/enviar" />" method="post">
           <table>
              <tr>
                 <tr>
                    <td class="info"><fmt:message key="nome.contato.form"/><span id="defined">*</span></td>
                    <td><input type="text" name="contato.nome" id="nome-client" size="30" maxlength="200" value="${contato.nome}"/></td> 
                 </tr>
                 <tr>
                    <td class="info"><fmt:message key="email.contato"/><span id="defined">*</span></td>
                    <td><input type="text" name="contato.email" id="email-client" size="50" maxlength="250" value="${contato.email}"/></td> 
                 </tr>
                 <tr>
                    <td class="info"><fmt:message key="telefone.contato"/></td>
                    <td><input type="text" name="contato.telefone"  id="telefone" size="20" value="${contato.telefone}" /></td>
                 </tr>
                 <tr>
                    <td></td>
                    <td class="info-ex"><fmt:message key="exemplo.telefone.contato"/></td>
                 </tr>
                 <tr>
                    <td class="info" valign="top"><fmt:message key="mensagem.contato" /><span id="defined">*</span></td>
                    <td class="info">
                        <textarea rows="10" name="contato.mensagem" id="mensagem">${contato.mensagem}</textarea>
                    </td>
                 </tr>
                 <tr>
                 <td></td>
                 <td><input type="submit" name="enviar" value="Enviar" id="send"/></td>
                 </tr>
           </table>
        </form>
      </div>
      
      <div id="banner">
      
      </div>
   </div>
</div>

<%@ include file="../../../footer.jsp" %>
