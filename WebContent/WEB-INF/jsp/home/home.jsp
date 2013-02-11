<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles</title>
<link href="css/bootstrap.css" rel="stylesheet">
    	<style type="text/css">
      		body {
        		padding-top: 60px;
        		padding-bottom: 40px;
      		}
    	</style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="url" content="http://www.loucaliza.com.br" />
<meta name="title" content="Econtre os locais e servi�os que est�o pr�ximos de voc� como bares, restaurantes, bancos, sal�es de beleza, shoppings e muito mais." />
<meta name="keys" content="busca, encontrar, locais, localiza��es, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, sal�es de beleza, cl�nicas, hospitais, cinema, academia" />
<meta name="keywords" content="busca, encontrar, locais, localiza��es, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, sal�es de beleza, cl�nicas, hospitais, cinema, academia" />
<meta name="description" content="O Wrahgles ajuda voc� a encontrar os locais e servi�os que est�o pr�ximos de voc�. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, sal�es de beleza, cl�nicas, hospitais, cinema, academia" />
<meta name="abstract" content="O Wrahgles ajuda voc� a encontrar os locais e servi�os que est�o pr�ximos de voc�. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, sal�es de beleza, cl�nicas, hospitais, cinema, academia" />

<meta name="google-site-verification" content="QeV-cZPQkppinYbnmmZUY6R3zn7foOYxMPykuCN9jfU" />

<!-- Default -->

 <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" /> 
 <link rel="stylesheet" href="<c:url value="/js/jquery/demos/demos.css" />" type="text/css"/>
 <link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
 <link rel="stylesheet" href="<c:url value="/css/jscroll.css"/>" type="text/css"  media="all" />
 <link rel="stylesheet" href="<c:url value="/css/jquery-ui.css"/>" type="text/css" media="all" />    
 <link rel="stylesheet" href="<c:url value="/css/basic.css" />" type="text/css" media="screen" />
 
 <script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.pagination.js" />"></script>
 <script type="text/javascript" src="<c:url value="/js/mapiconmaker.js" />"></script>
 <script type="text/javascript" src="<c:url value="/js/home/home.js" />" ></script>
 <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false" ></script>    
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
 <script type="text/javascript" src="<c:url value="/js/ui/jquery-ui.min.js"/>" ></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.jscrollpane.min.js"/>"></script>  
 <script type="text/javascript" src="<c:url value="/js/reflection.js"/>"></script>        
 <script type="text/javascript" src="<c:url value="/js/jquery.simplemodal.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/basic.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/maps.js"/>" ></script>
   
    

<script type="text/javascript">
$(window).resize(function() {ajustaDivs();});
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

<c:if test="${not empty zoomPesquisa}">
	<body onload="carregaMapaHome(${zoomPesquisa})" >
</c:if>
<c:if test="${empty zoomPesquisa}">
	<body onload="carregaMapaHome(11)" >
</c:if>
	
<%@ include file="../../../menu.jsp" %>

<div id="content-home">
  <div id="col-esq">
     <div id="list"></div>
     <div id="paginacao"></div>
  </div>
  
  <div id="col-map">
       <div id="mapa"></div>
  </div>


</div>

<%@ include file="../../../footer.jsp" %>


			<script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
			<script src="assets/js/jquery.js"></script>
			<script src="assets/js/google-code-prettify/prettify.js"></script>
			<script src="assets/js/bootstrap-transition.js"></script>
			<script src="assets/js/bootstrap-alert.js"></script>
			<script src="assets/js/bootstrap-modal.js"></script>
			<script src="assets/js/bootstrap-dropdown.js"></script>
			<script src="assets/js/bootstrap-scrollspy.js"></script>
			<script src="assets/js/bootstrap-tab.js"></script>
			<script src="assets/js/bootstrap-tooltip.js"></script>
			<script src="assets/js/bootstrap-popover.js"></script>
			<script src="assets/js/bootstrap-button.js"></script>
			<script src="assets/js/bootstrap-collapse.js"></script>
			<script src="assets/js/bootstrap-carousel.js"></script>
			<script src="assets/js/bootstrap-typeahead.js"></script>
			<script src="assets/js/bootstrap-affix.js"></script>
			<script src="assets/js/application.js"></script>

</body>
</html>