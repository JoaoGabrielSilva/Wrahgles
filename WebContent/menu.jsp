<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<link href="css/bootstrap.css" rel="stylesheet">
    	<style type="text/css">
      		body {
        		padding-top: 60px;
        		padding-bottom: 40px;
      		}
    	</style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
	<script src="<c:url value="/js/utils.js" />" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/js/menu.js" />" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="../css/base.css" type="text/css" />
    <link rel="stylesheet" href="../css/style.css" type="text/css" />
    <link rel="stylesheet" href="../css/jscroll.css" type="text/css" media="all" />
    <link rel="stylesheet" href="../css/jquery-ui.css" type="text/css" media="all" />    
    <link rel="stylesheet" href='../css/basic.css' type="text/css" media='screen' />
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/bootstrap-responsive.css" rel="stylesheet">
    

<div id="wrahgles-page">
  <div id="global">
    <div class="navbar navbar-inverse navbar-fixed-top">
		                     
		                     
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
					<a class="brand" href="#">Wrahgles</a>
						<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active">
								<a href="#">Home</a>
							</li>
							<li class="">
								<a href="perfil.html">Meu Perfil</a>
							</li>
							<li class="">
								<a href="oferta-cupom.html">Anúncios</a>
							</li>
							<li class="">
								<a href="user.html">Contato</a>
							</li>
							<li class="">
								<a href="contato.html">Ajuda</a>
							</li>
							<li class="">
								<a href="lista-local.html">Locais</a>
							</li>
							<li class="">
								<a href="user.html">Meus Amigos</a>
							</li>
						</ul>
			
							<div class="btn-group">
								<a class="btn btn-primary" href="#"><i class="icon-user icon-white"></i>Sair</a>
							</div>
						</div><!--/.nav-collapse -->
			</div>
		</div>
    </div>
    <!-- MENSAGEM -->
    <c:if test="${not empty mensagem}">
    <div id="effect-success">
        <span>${mensagem}</span>
    </div>
    </c:if>
    
    <div id="header">
       <div id="search">
         <div id="search-info">
            <div id="social-rede-header">
                <span><fmt:message key="siga.menu"/></span>
                <a href="" target="_blank"><img src="<c:url value="/img/sign1.gif" />" alt="Facebook" title="Facebook"/></a>
                <a href="" target="_blank"><img src="<c:url value="/img/sign2.gif" />" alt="Twitter" title="Twitter"/></a>
                <a href="" target="_blank"><img src="<c:url value="/img/sign3.gif" />" alt="Orkut" title="Orkut"/></a>
            </div>
            <div id="adm">
               <c:if test="${empty usuarioLogado.usuario}">
	               <a href="<c:url value="/usuario/form"/>"><span id="asm-info"><fmt:message key="cadastro.menu" /></span></a>
	               <a href="<c:url value="/home/logar"/>"><span id="asm-info"><fmt:message key="login.menu" /></span></a>
	           </c:if>
	           
	           <!-- Usuario logado -->
	           <c:if test="${not empty usuarioLogado.usuario}">
	              <a href="<c:url value="/usuario/logoff" />"><span id="asm-info"><fmt:message key="sair.menu"/></span></a>
	              <a href="<c:url value="/usuario/alterar" />"><span id="asm-info"><fmt:message key="alterar.dados.menu"/></span></a>
	              <a href="<c:url value="/localizacao/form" />"><span id="asm-info"><fmt:message key="incluir.local.menu"/></span></a>
	           
	           <c:if test="${usuarioLogado.usuario.role eq 'admin' or usuarioLogado.usuario.role eq 'moderador'}">
	              <a href="<c:url value="/admin" />"><span id="asm-info"><fmt:message key="admin.menu"/></span></a> 
	           </c:if>
	           
	           <a href="<c:url value="/perfil/meuPerfil" />"><span id="adm-email">${usuarioLogado.usario.email}</span></a>
	          </c:if>
	      </div>
       </div>
             <div id="search-location">
                  	
                    	<form method="post" id="frmBusca" name="frmBusca" action="<c:url value="/busca/" />">
                            <div id="search-cat">
                                <c:if test="${empty buscaSession.categoria}">
                   					<input type="text" name="categoriaPesquisa" id="categoriaPesquisa" value="${buscaSession.categoriaRandon}" onkeypress="return clickEnterSubmitForm(event);" />
                   				</c:if>
                   				<c:if test="${not empty buscaSession.categoria}">
                   					<input type="text" name="categoriaPesquisa" id="categoriaPesquisa" value="${buscaSession.categoria}" onkeypress="return clickEnterSubmitForm(event);" />
                   				</c:if>
                                <span class="subtit"><span><fmt:message key="ex.categoria.menu"/></span> <fmt:message key="exemplo.categoria.menu"/></span>
                            </div>
                            <div id="search-end">
                                <span class="tit"><fmt:message key="endereco.menu"/></span>
                                <c:if test="${empty enderecoPesquisa}">
                   			  		<input type="text" name="enderecoPesquisa" id="enderecoPesquisa" value="São Paulo - SP" onkeypress="return clickEnterSubmitForm(event);" />
                   				</c:if>
                   				<c:if test="${not empty enderecoPesquisa}">
                   					<input type="text" name="enderecoPesquisa" id="enderecoPesquisa" value="${enderecoPesquisa}" onkeypress="return clickEnterSubmitForm(event);" />
                   				</c:if>
                                <span class="subtit"><span><fmt:message key="ex.categoria.menu"/></span> <fmt:message key="exemplo.endereco.menu"/></span>
                            </div>
                            <div id="btn-send">
								<!-- <a href="#" class="modal-index"><input type="submit"  value="" /></a> -->
								<input type="button" name="input-search" title="Pesquisar" value="" id="input-search" onclick="callBusca();" />
                           </div>                        
                   		</form>	
               </div>
	      </div>
	
  <div id="submenu">
     <div id="submenu-list">
          <span>Link rápidos:</span>
          <a href="<c:url value="/localizacao/form" />"><span><fmt:message key="incluir.local.menu"/></span></a>
     </div>
   </div>
</div>


