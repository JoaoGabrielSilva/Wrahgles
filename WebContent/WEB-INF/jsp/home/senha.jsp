<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="url"  content="http://www.wrahgles.com.br/logar">
<meta name="title" content="Entrar na minha conta Wrahgles">
<meta name="keys" content="busca, encontrar, locais, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza,clinicas,hospitais, cinema, academia">
<meta name="keyword" content="busca, encontrar, locais, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza,clinicas,hospitais, cinema, academia">
<meta name="description" content="O Wrahgles ajuda você a encotrar locais e serviços que estão próximos de você. Como bancos, bares, restaurantesm baladas,shopping, club, lojas, salões de beleza, clinicas, hospitais, cinema, academia">
<meta name="abstract" content="O Wrahgles ajuda você a encotrar locais e serviços que estão próximos de você. Como bancos, bares, restaurantesm baladas,shopping, club, lojas, salões de beleza, clinicas, hospitais, cinema, academia">

<!-- default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico"/>"/>
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/home/modalLogin.js" />"></script>
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
<div id="wrahgles-page">
<div id="global"></div>
<div id="content">
 <div id="position-log">
     <div id="logotipo">
        <a href="<c:url value="/" />"><img src="<c:url value="/img/logo.jpg"/>" alt="Wrahgles" title="Wrahgles"/></a>
     </div>
     <div id="decor">
         <div id="rede-social">
           <div id="ads-login"> 
             <a href="<c:url value="anuncio/form" />"><img src="<c:url value="/img/anuncio_login.gif" />" alt="Anúncios" title="Anúncios"></a>
           </div>
           <div>
              <p><fmt:message key="criar.conta.login"></fmt:message></p>
           </div>
           <div id="conta">
              <a href="<c:url value="/anuncio/form"/>"><span></span></a>
           </div>
         </div>
         <div id="log-wrahgles">
            <c:if test="${not empty errors }">
              <c:forEach items="${errors}" var="error">
                <h2>${error.message}</h2>
            </c:forEach>
            </c:if>
            
            <h3><fmt:message key="esqueceu.senha.enviar" /></h3>
             <form name="target" id="target" action="<c:url value="/usuario/senha"/>" method="post" >
                <table>
                   <tr>
                        <td><span><fmt:message key="campo.email" /></span></td>
                   </tr>
                   <tr>
                      <td><input type="text" name="usuario.email" id="login" value="$usuario.email">
                   </tr>
                   <tr>
                      <td><input type="submit" name="" id="botao-senha" value=""/></td>
                   </tr>
                </table>
             </form>
         </div>
     </div>
 </div>
</div>
</div>
</body>
</html>