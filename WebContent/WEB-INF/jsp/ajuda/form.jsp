<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Ajuda</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="url" content="http://www.wrahgles.com.br/ajuda/form" />
<meta name="title" content="Ajuda" />
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
<link type="text/css" href="<c:url value="/js/jquery/themes/base/jquery.ui.all.css" />" rel="stylesheet" />
<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/jquery/external/jquery.bgiframe-2.1.1.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.core.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.widget.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.mouse.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.button.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.draggable.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.position.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.resizable.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.dialog.js" />" ></script> 
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.effects.core.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.tabs.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery.ui.accordion.js" />" ></script>

<c:if test="${empty menuTabs}">
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
		$("#tabs").tabs();
	});
</script>
</c:if>

<c:if test="${not empty menuTabs and menuTabs eq '1'}">
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
		$("#tabs").tabs({selected: 1});
	});
</script>
</c:if>

<c:if test="${not empty menuTabs and menuTabs eq '2'}">
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
		$("#tabs").tabs({selected: 2});
	});
</script>
</c:if>
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

<div id="geral">
	<!-- inicio tabs -->
	<div id="tabs">
		<ul>
			<%@ include file="menu.html"  %>
		</ul>
		<div id="tabs-1">
			<div id="accordion">
            	<%@ include file="perguntas.html"  %>            
            </div> 
		</div>
		<div id="tabs-2">
			<%@ include file="termos.jsp"  %>
		</div>
		<div id="tabs-3">
			<%@ include file="politica.jsp"  %>
		</div>
	</div>

</div>

<%@ include file="../../../footer.jsp" %>