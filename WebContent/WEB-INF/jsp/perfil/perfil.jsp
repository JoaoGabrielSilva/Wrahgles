<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="url" content="http://www.wrahgles.com.br/perfil/${usuario.id}/${usuario.nomeFormat}"/>
<meta content="${usuario.nome}" name="title">


<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />

<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>

<title>${usuario.nome}</title>
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
    <span class="title"><fmt:message key="opinioes"/></span>
 </div>
 
 <!-- mensagem de inclusao de primeiro comentario -->
 <c:if test="${empty comentarios}"></c:if>
 
 <!-- iterando nos comentarios -->
 <c:if test="${not empty comentarios}">
    <c:forEach items="${comentarios}" var="com" varStatus="i">
       <div class="full-location">
       <div class="location-img">´
	        <c:if test="${empty com.foto}">
	           <img src="<c:url value="/img/no-photo.gif"/>" alt="${com.comentario.localizacao.nome}" title="${com.comentario.localizacao.nome}"/>
	        </c:if>
	        <c:if test="${not empty com.foto}">
	           <img src="<c:url value="${com.foto.imagem}"/>" alt="${com.comentario.localizacao.nome}" title="${com.comentario.localizacao.nome}" width="80"/>
	        </c:if>
	   </div>
	   <div class="info-location">
	    <span class="star-p"><strong><a href="<c:url value="/local/${com.comentario.localizacao.id}/${com.comentario.localizacao.nomeFormat}"/>" class="title-location">${com.comentario.localizacao.nome}</a></strong><img src="<c:url value="/images/notas/pequenas/${com.comentario.nota}.png" />"/></span>
	     
	     <span>${com.comentario.comentario}</span>
	     <div class="info-location-client">
	        <div class="location-client">
	           <span><fmt:message key="enviado.perfil"/>${com.comentario.dtComentarioFormat} por <a href="<c:url value="/perfil/${com.comentario.usuario.id}/${com.comentario.usuario.nomeFormat}"/>">${com.comentario.usuario.nome}</a></span>
	        </div>
          </div>
	   </div>
	 </div>
    </c:forEach>
 </c:if>
</div>

<%@ include file="colDirPerfil.jsp" %>

</div>

<%@ include file="../../../footer.jsp" %>
