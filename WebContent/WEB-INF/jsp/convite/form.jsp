<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wrahgles - Busca de amigos</title>

<meta name="url" content="http://www.wrahgles.com.br/convite/form" />
<meta name="title" content="Econtre seus amigos que estão no wrahgles" />
<meta name="keys" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia, amigos, redes sociais" />
<meta name="keywords" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia, amigos, redes sociais" />
<meta name="description" content="Encontre seus amigos que estão cadastrados no wrahgles e saiba quais lugares seus amigos estão frequentando, o wrahgles ajuda você a se aproximar cada vez mais dos seus amigos!" />
<meta name="abstract" content="Encontre seus amigos que estão cadastrados no wrahgles e saiba quais lugares seus amigos estão frequentando, o wrahgles ajuda você a se aproximar cada vez mais dos seus amigos!" />



<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
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

<%@ include file="../../../menu.jsp"  %>

<div id="geral">
  <div id="col-esq-invit">
   <h3>Encontre seus amigos com o Wrahgles</h3>
   <p>Selecione abaixo qual caixa de emails que deseja procurar sues amigos. Não se preocupe! O Wrhgles não iremos guardar sua senha da caiza de emails, muito menos enviar spam para seus contatos.</p>
   <p>Para maiores esclarecimentos, <a href="#">clique aqui</a> e veja a Política de privacidade do Wrahgles.Para sugestões ou dúvidas,  <a href="#">clique aqui</a> e envie seu contato para o Wrahgles. Suas opiniões ou críticas são muito importantes para nós.</p><div></p>
   <h3>Acesse sua caixa de emails</h3>
   <p>Selecione qual caixa de emails deseja encontrar seus amigos para fazer parte do Wrahgles</p>
   <form action=""<c:url value="/convite/listPerfil"/>" method="post">
        <span id="invit-google">
           <span class="img-invit"><img src="../img/invit-google.jpg" alt="Google mail" title="Google mail"/></span>
           <input type="radio" name="conta" id="conta" value="google">
        </span>
        <span id="invit-yahoo">
        <span class="img-invit"><img src="../img/invit-yahoo.jpg" alt="Yahoo mail" title="Yahoo mail" /></span>
                            <!-- <input type="radio" name="conta" id="conta" value="yahoo" /> -->
               EM DESENVOLVIMENTO!
        </span>
        <span id="invit-msn">
        <span class="img-invit"><img src="../img/invit-msn.jpg" alt="MSN / Hotmail" title="MSN / Hotmail" /></span>
           <!-- <input type="radio" name="conta" id="conta" value="hotmail" /> -->
         	EM DESENVOLVIMENTO!
        </span>
        <div id="invit">
           <p>Insira seus dados na conta abaixo. Não se preocupe,  Nós do Wrahgles não iremos guardar sua senha da caixa de emails, muito menos enviar spam para seus contatos.</p>
           <table>
              <tr>
                <td class="name">Usuário / login:</td>
                <td class="input">
                   <input type="text" name="email" id="email" value="${usuarioLogado.usuario.email}">
                </td>
              </tr>
              <tr>
                  <td class="name">Senha / password:</td>
                  <td colspan="3" class="input">
                    <input type="password" name="senha" id="senha"/>
                  </td>
              </tr>
              <tr>
                <td colspan="4">
                  <input type="submit" name="" value="Buscar" id="send">
                </td>
              </tr>
           </table>
        </div>
   </form>
  </div>
         
            <%@ include file="colDir.jsp"  %>
</div>

<%@ include file="../../../footer.jsp"  %>