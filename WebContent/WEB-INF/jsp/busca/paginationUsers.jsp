<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />
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

 <c:if test="${not empty usuarioList}">
    <c:forEach var="usuario" items="${usuarioList}">
      <div class="users">
        <c:if test="${empty usuario.fotoPerfil}">
           <a href="<c:url value="/perfil/${usuario.id}/${usuario.nomeFormat }"/>"><img src="<c:url value="/img/no-photo-user-45X45.jpg"/>" alt="${usuario.nome}" title="${usuario.nome}"/></a>
        </c:if>
        <c:if test="${not empty usuarioList}">
            <a href="<c:url value="/perfil/${usuario.id}/${usuario.nomeFormat }"/>"><img src="<c:url value="${usuario.fotoPerfil }"/>" alt="${usuario.nome}" title="${usuario.nome}" style='max-width: 65px; max-height: 65px;'/></a>
        </c:if>
        
        <span class="title"><a href="<c:url value="/perfil/${usuario.id}/${usuario.nomeFormat}"/>">${usuario.nome}</a></span>
        <span>cadastrado(a) em ${usuario.dataCadastroFormat}</span>
      
      </div>
    </c:forEach>
 </c:if>
</body>
</html>