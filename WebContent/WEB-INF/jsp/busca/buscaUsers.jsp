<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Procurar Usuários</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="url"  content="http://www.wrahgles.com.br/busca/users">
<meta name="title" content="Encontre seus amigos que estão no Wrahgles">
<meta name="keys" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia, amigos, redes sociais" />
<meta name="keywords" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia, amigos, redes sociais" />
<meta name="description" content="Encontre seus amigos que estão cadastrados no Wrahgles e saiba quais lugares seus amigos estão frequentando, o Wrahgles ajuda você a se aproximar cada vez mais dos seus amigos!" />
<meta name="abstract" content="Encontre seus amigos que estão cadastrados no Wrahgles e saiba quais lugares seus amigos estão frequentando, o Wrahgles ajuda você a se aproximar cada vez mais dos seus amigos!" />

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/js/jquery/demos/demos.css"/>"  type="text/css"/>
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.pagination.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/busca/buscaUsers.js" />"></script>
<style type="text/css"> 
#paginacao {
   float:left;
   font-size:13px;
   height:40px;
   margin:2px 0 0 15px;
   padding:15px 0 0;
   width:300px;
}
#paginacao a {
    text-decoration: none;
	border: solid 1px #AAE;
	color: #15B;
}
#paginacao a, #paginacao span {
    display: block;
    float: left;
    padding: 0.3em 0.5em;
    margin-right: 5px;
	margin-bottom: 5px;
}
#paginacao .current {
    background: #26B;
    color: #fff;
	border: solid 1px #AAE;
}
#paginacao .current.prev, #paginacao .current.next{
	color:#999;
	border-color:#999;
	background:#fff;
}
</style>
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

<input type="hidden" name="qtdUsuarios" id="qtdUsuarios" value="${count}">

<div id="geral">
  <div id="col-esq-user">
     <p>Aqui você encotrará todas as pessoas que se cadastraram no Wrahgles. Poderá encontrar seus amigos e também poderá  formar mais amigos, que frequentam os mesmos locais que você.</p>
     <div id="search-user">
        <form action=""<c:url value="/busca/users" />" method="post">
           <span><input type="text" name="name" id="name" value="${name}" /></span>
           <span><input type="submit" name="" id="send" value="Procurar" /></span>
        </form>
     </div>
     <div>
          	<div id="Searchresult"></div>                    
            <div id="paginacao"></div>
     </div>    
    
  </div>
  <div id="col-dir-wrahgles">
  
    <div class="lista-corpo-gold">
       <span class="title">Encontre mais amigos</span>
       <p>Utilize os recursos do Wrahgles para verificar sua lista de contatos da caixa de emails e ver se algum de seus amigos já se encontra em nosso site. Ele também terá essa mesa opção para convidar amigos</p>
       <img alt="" title="" src="<c:url value="/img/convite-email-2.jpg"/>">
       <a href="<c:url value="/convite/form" />"><span>Encontrar meus amigos</span></a>
    </div>
    
    <div class="lista-corpo">
       <span class="title">+ Comentados</span>
       <div class="list-info">
       
         <c:forEach var="mCom" items="${maisComentados}" varStatus="1">
           <div>
             <span class="order-list">${i.count}</span>
             <a href="<c:url value="/local/${mCom.local.id}/${mCom.local.nomeFormat}"/>"><span>${mCom.local.nome}</span></a>
           </div>
         </c:forEach>
       </div>
    </div>
    
    <div class="lista-corpo">
      <span class="title">Maiores contribuidores</span>
       
       <c:forEach var="mUsers" items="${usuarioMaisComentarios}">
         <div class="list-high">
           <c:if test="${empty mUsers.usuario.fotoPerfil}">
              <a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}" />"><img src="<c:url value="/img/no-photo-user-45X45.jpg" />" alt="${mUsers.usuario.nome}" title="${mUsers.usuario.nome}"/></a>
           </c:if>
           <c:if test="${not empty mUsers.usuario.fotoPerfil}">
              <a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}"/>"><img src="<c:url  value="/${mUsers.usuario.fotoPerfil}"/>" alt="${mUsers.usuario.nome}" title="${mUsers.usuario.nome}" style='max-width: 45px;max-height: 45px;'/></a>
           </c:if>
           
           <div class="info-list-high">
               <span><a href='<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}"></c:url>'>${mUsers.usuario.nome} (${mUsers.qtdComentarios} opniões)</a></span>
               <span>Última: <a href='<c:url value="/local/${mUsers.ultimoComentario.localizacao.id}/${mUsers.ultimoComentario.localizacao.nomeFormat}"></c:url>'></a></span> 
           </div>
         </div>
       </c:forEach>
    </div>
    
    <div class="lista-corpo">
       <span class="title">Novos integrantes Wrahgles</span>
       
       
        <c:forEach var="us" items="${newUsers}">
         <div class="list-high">
            <c:if test="${empty us.fotoPerfil}">
                 <a href="<c:url value="/perfil/${us.id}/${us.nomeFormat}" />"><img src="<c:url value="/img/no-photo-user-45X45.jpg" />" alt="${us.nome}" title="${us.nome}"/></a>
            </c:if>
            <c:if test="${not empty us.fotoPerfil}">
               <a href="<c:url value="/perfil/${us.id}/${us.nomeFormat}" />"><img src="<c:url value="/${us.fotoPerfil}" />" alt="${us.nome}" title="${us.nome}" style='max-width: 45px; max-height: 45px;'/></a>
            </c:if>
            
            <div class="info-list-high">
               <span><a href="<c:url value="/perfil/${us.id}/${us.nomeFormat}"/>">${us.nome}</a></span>
            </div>
         </div>
        </c:forEach>
    </div>
  </div>
</div>

<%@ include file="../../../footer.jsp" %>