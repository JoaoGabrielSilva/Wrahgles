<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Anuncio</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

</script>

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
</head>
<body>

<%@ include file="../../../menu.jsp" %>

<div id="content">

  <div id="position-contato">
   <p class="info-cad" align="center"><fmt:message key="link.patrocinado.anuncio"/></p>
   
   <div id="wrahgles-contato">
      <!-- add errors -->
       <c:if test="${not empty errors }">
         <c:forEach items="${errors}" var="error">
            <p><span class="campos">(<span id="defined">*</span>)${error.message}</span> </p>
         </c:forEach>
      </c:if>
      
      
      <h3><fmt:message key="preencha.anuncio.form" /></h3>
      <p><fmt:message key="campos.com.anuncio" /><span id="defined">*</span><fmt:message key="campo.obrigatorio.anuncio"/></p>
      
      <form action="<c:url value="/anuncio/enviar"/>" name="contato" id="contato" method="post">
      
        <table>
          <tr>
             <td class="info"><fmt:message key="nome.anuncio.form"/><span id="defined">*</span></td>
             <td><input type="text" name="contato.nome" id="nome-client" size="30" maxlength="200" value="${contato.nome}"/></td>
          </tr> 
          <tr>
             <td class="info"><fmt:message key="email.anuncio"/><span id="defined">*</span></td>
             <td><input type="text" name="contato.email" id="email-client" size="50" maxlength="250" value="${contato.email}"/></td>
          </tr>        
          <tr>
             <td class="info"><fmt:message key="telefone.anuncio"/><span id="defined">*</span></td>
             <td><input type="text" name="contato.telefone" id="telefone" size="20" value="${contato.telefone}"/></td>
          </tr>  
          <tr>
             <td></td>
             <td class="info-ex"><fmt:message key="exemplo.telefone.anuncio"/></td>
          </tr>
          <tr>
             <td class="info" valign="top"><fmt:message key="mensagem.anuncio" /><span id="defined">*</span></td>
             <td class="info">
               <textarea rows="10" name="contato.mensagem" id="message">${contato.mensagem}</textarea>
          </td>
          </tr>
          <tr>
            <td></td>
            <td><input type="submit" name="enviar" value="Enviar" id="send"/>  </td>
          </tr>
        </table>
      </form>
   </div>
   <div id="banner">
   
   </div>
  </div>

</div>

<%@ include file="../../../footer.jsp" %>

</body>
</html>