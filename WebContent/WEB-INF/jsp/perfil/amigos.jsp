<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="url" content="http://www.loucaliza.com.br/perfil/amigos/${usuario.id}/${usuario.nomeFormat}" />
<meta name="title" content="${usuario.nome} - Amigos" />

<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />

<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>


<title>${usuario.nome} - Amigos</title>
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

<%@ include file="../../../menu.jsp"  %>

<%@ include file="menuPerfil.jsp"  %>

 <div>
   <span class="title"><fmt:message key="meus.amigos"/></span>
 </div>
 
 <c:if test="${not empty todosAmigos }">
  <div id="friends">
    
     <c:forEach  var="tAmigo" items="todosAmigos">
      <c:if test="${empty tAmigo.id.usuarioAmigo.fotoPerfil}">
         <a href="<c:url value="/perfil/${tAmigo.id.usuarioAmigo.id}/${tAmigo.id.usuarioLogado.nomeFormat}"/>"><img src="<c:url value="/img/no-photo-user-80X80.jpg" />" alt="${ tAmigo.id.usuarioLogado.nome}" title="${tAmigo.id.usuarioLogado.nome}"/></a>
      </c:if>
          <c:if test="${not empty tAmigo.id.usuarioAmigo.fotoPerfil}">
         <a href="<c:url value="/perfil/${tAmigo.id.usuarioAmigo.id}/${tAmigo.id.usuarioLogado.nomeFormat}"/>"><img src="<c:url value="/${tAmigo.id.usuarioAmigo.fotoPerfil}" />" alt="${ tAmigo.id.usuarioLogado.nome}" title="${tAmigo.id.usuarioLogado.nome}" width="80"/></a>
      </c:if>
      
      <span><a href="<c:url value="/perfil/${tAmigo.id.usuarioAmigo.id}" />">${tAmigo.id.usuarioAmigo.nome}</a></span>
    </c:forEach>
  
  </div>
 
 </c:if>
 
 </div>
 </div>
 
 <%@ include file="colDirPerfil.jsp" %>