<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${usuario.id == null ? 'Cadastrar Usuário': 'Alterar Usuário'}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="url" content="http://www.Wrahlges.com.br/usuario/${usuario.id == null ? 'form': 'alterar'}" />
<meta name="title" content="${usuario.id == null ? 'Cadastrar Usuário': 'Alterar Usuário'}" />
<meta name="keys" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="keywords" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="description" content="O Wrahlges ajuda você a encontrar os locais e serviços que estão próximos de você. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="abstract" content="O Wrahlges ajuda você a encontrar os locais e serviços que estão próximos de você. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />

<meta name="google-site-verification" content="EV01HmRqaNgP1rUcTmlQaU-Afhf_AwQ7JTwnCHOXpCE" />

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/usuario/form.js" />" ></script>
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

<%@ include file="../../../menu.jsp" %>

<div id="content">
   <div id="position">
       <div id="wrahgles-cadastro">
       
       <!--  add errors -->
       <c:if test="${not empty errors}">
          <c:forEach items="${errors}"  var="error">
          <p><span class="campos">(<span id="defined">*</span>) ${error.message}</span></p>
          </c:forEach>
       </c:if>
       
       <h3><fmt:message key="preencha.campo.form"/>${usuario.id == null ? 'realizar seu cadastro' :'alterar seus dados'}</h3>
       <p><fmt:message key="campo.com.form"/><span id="defined">*</span><fmt:message  key="campo.obrigatorio.form"/></p>
       
       <form name="cadastro-pessoal" action="<c:url value="/usuario/salvar"/>" enctype="multipart/form-data" method="post" id="cadastro-pessoal">
          <input type="hidden" name="usuario.id"  value="${usuario.id}"/>
       
          <table>
             <tr>
                <td class="info" rowspan="2"><fmt:message key="avatar.form" /></td>
                <td>
                 <c:if test="${empty usuario.fotoPerfil}">
                     <img src="<c:url value="/img/no-photo-user-80X80.jpg" />"/>
                 </c:if>
                 <c:if test="${not empty usuario.fotoPerfil}">
                     <img src="<c:url value="${usuario.fotoPerfil}"/>" width="80px" height="80px"/>
                     <input type="hidden" name="usuario.fotoPerfil" value="${usuario.fotoPerfil}"></input>
                 </c:if>
                </td>
             </tr>
             <tr>
                <td>
                  <input type="file" id="file" name="file"/>
                </td>
             </tr>
             <tr>
                <td class="info"><fmt:message key="nome.form"/><span id="defined">*</span></td>
                <td><input type="text" name="usuario.nome" id="nome-client" value="${usuario.nome}"></input></td>
             </tr>
             <tr>
                <td class="info"><fmt:message key="campo.email"/><span id="defined">*</span></td>
                <td><input type="text" name="usuario.email" id="email-client" value="${usuario.email}"></input></td>
             </tr>
             <tr>
                <td class="info"><fmt:message key="campo.senha"/><span id="defined">*</span></td>
                <td><input type="password" name="usuario.senhaTemp" id="pass" value="${usuario.senhaTemp}"></input></td>
             </tr>
             <tr>
               <td></td>
               <td class="info-ex"><fmt:message key="caracter.form"/></td>
             </tr>
             
             <tr>
                <td class="info"><fmt:message key="confirma.senha.form"/><span id="defined">*</span></td>
                <td><input type="password" name="confSenha" id="conf-pass" value="${confSenha}"></input></td>
             </tr>
             
             <c:if test="${empty usuario.id}">
               <tr>
                  <td></td>
                  <td id="term"><input type="checkbox" name="termos" id="valid-term" /><fmt:message key="declaro.li.form"/><a href="<c:url value="/ajuda/termos" />" target="_blank"><fmt:message key="termos.servico.form"/></a> e <a href="<c:url value="/ajuda/politica" />" target="_blank"><fmt:message key="politica.form"/></a></td>
               </tr>
             </c:if>
             <tr>
               <td></td>
               <td id="novidades"><input type="checkbox" name="usuario.flgInfoWrahgles" checked="checked" id="news"/><fmt:message key="receber.informacao.form"></fmt:message></td>
             </tr>
             <tr>
                <td></td>
                <td><input type="submit" name="cadastrar" value="${usuario.id == null ? 'Cadastrar': 'Alterar'}" id="send" /></td>
             </tr>
           </table>
       </form>
       </div>
   </div>
</div>

<%@ include file="../../../footer.jsp" %>

</body>
</html>