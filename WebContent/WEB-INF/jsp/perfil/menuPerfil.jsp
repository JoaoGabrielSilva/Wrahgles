<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div id="geral">
      <div id="col-esq-perfil">
         <div id="session">
          <div id="menu-session">
            <div>
                <a href="<c:url value="/perfil/${usuario.id}/${usuario.nomeFormat}"/>"><span><fmt:message key="perfil"/></span></a>
            </div>
            <div>
               <a href="#"><span><fmt:message key="detalhes"/></span></a>
            </div>
            <div>
              <a href="<c:url value="/perfil/${usuario.id}/${usuario.nomeFormat}"/>"><span><fmt:message key="opinioes"/> (${qtdComentarios })</span></a>
            </div>
            <div>
                <a href="#"><span><fmt:message key="fotos.detalhe"/> (${qtdFotos})</span></a>
            </div>
            <div>
                <a href="<c:url value="/perfil/amigos/${usuario.id}/${usuario.nomeFormat}"/>"><span><fmt:message key="amigos"/> (${qtdAmigos})</span></a>
            </div>
            <div>
              <a href="#"><span><fmt:message key="mapa.detalhe"/></span></a>
            </div>
          </div>
          
          <c:if test="${empty usuario.fotoPerfil}">
            <img src="<c:url value="/img/no-photo-2.gif" />" alt="${usuario.nome}" title="${usuario.nome}" />
          </c:if>
          <c:if test="${not empty usuario.fotoPerfil}">
            <img src="<c:url value="/${usuario.fotoPerfil}" />" alt="${usuario.nome}" title="${usuario.nome}" width="100"/>
          </c:if>
          
          <div id="info-session">
            <span id="title">${usuario.nome}</span>
            <div>
                <span class="amigo"><a href="<c:url value="/perfil/amigos/${usuario.id}/${usuario.nomeFormat}" />">${qtdAmigos} <fmt:message key="amigos"/></a></span><span class="opiniao"><a href="<c:url value="/perfil/${usuario.id}/${usuario.nomeFormat}" />">${qtdComentarios} <fmt:message key="opinioes"/></a></span>
            </div>
            <div id="send-session">
              <c:if test="${empty usuarioLogado.usuario or empty statusPerfil}">
                <a href="<c:url value="/perfil/addAmigo/${usuario.id}" />">
                  <span id="add-amigo"></span>
                </a>
              </c:if>
              <c:if test="${not empty statusPerfil and statusPerfil eq 'aguardandoAmizade'}">
                 <span id="location"><fmt:message key="aguardando.conf.amizade"/></span>
              </c:if>
              <c:if test="${not empty statusPerfil and statusPerfil eq 'confirmarAmigo' }">
                 <a href="<c:url value="/perfil/confirmarAmigo/${usuario.id}" />">
                   <span id="conf-amigo"></span>
                 </a>
              </c:if>
            
            </div>
           </div>
         </div>
         
         <c:if test="${not empty amigosPendentes}">
         <div class="info-perfil">
          <span class="title"><fmt:message  key="solicitacao.amizade"/></span>
          
          <c:if test="${qtdAmigosPendentes == 1 }"><p id="description-friend"><fmt:message key="voce.tem"/> ${qtdAmigosPendentes} <fmt:message key="solicita.amizade"/>solicitação de amizade</p></c:if>
          <c:if test="${qtdAmigosPendentes > 1 }"><p id="description-friend"><fmt:message key="voce.tem"/> ${qtdAmigosPendentes} <fmt:message key="solicitacao.amizade"/>solicitação de amizade</p></c:if>
          
          <c:forEach var="solicita" items="${amigosPendentes }">
            <div class="info-friend">
               <div class="s-friend">
                 <c:if test="${empty solicita.id.usuario.fotoPerfil}">
                   <a href="<c:url value="/perfil/${solicita.id.usuario.id}/${solicita.id.usuario.nomeFormat}" />"><img src="<c:url value="/img/no-photo-user-65X65.jpg" />" alt="${solicita.id.usuario.nome}" title="${solicita.id.usuario.nome}"/></a>
                 </c:if>
                 <c:if test="${not empty solicita.id.usuario.fotoPerfil}">
                    <a href="<c:url value="/perfil/${solicita.id.usuario.id}/${solicita.id.usuario.nomeFormat}" />"><img src="<c:url value="/${solicita.id.usuario.fotoPerfil}" />" alt="${solicita.id.usuario.nome}" title="${solicita.id.usuario.nome}" width="65"/></a>
                 </c:if>
                 
                 <span><a href="<c:url value="/perfil/${solicita.id.usuario.id}/${solicita.id.usuario.nomeFormat}"/>"><strong>${solicita.id.usuario.nome}</strong></a></span>
               
               </div>
               <div id="f-friend">
                  <a href="<c:url value="/perfil/${solicita.id.usuario.id}/${solicita.id.usuario.nomeFormat}" />"><span><fmt:message key="ver.perfil"/></span></a>
                  <a href="<c:url value="/perfil/recusarAmigo/${solicita.id.usuario.id}"/>"><span id="rec-friend"></span></a>
                  <a href="<c:url value="/perfil/confirmarAmigo/${solicita.id.usuario.id}"/>"><span id="add-friend"></span></a>
               </div>
            </div>
          </c:forEach>
         
         </div>
         </c:if>
