<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div id="col-dir-wrahgles">
       <div class="propaganda">
       
       </div>
       
       <div class="lista-corpo" id="borda"> 
          <span class="title"><fmt:message key="ultimas.fotos.postadas" /></span>
          <div id="photo">
           <c:if test="${not empty fotos}">
              <c:forEach var="foto" items="${fotos}" varStatus="i">
                 <a href="<c:url value="/local/${foto.localizacao.id}/${foto.localizacao.nomeFormat}" />"><img src="<c:url value="/${foto.imagem }" />" width="75" height="75" alt="${foto.localizacao.nome}" title="${foto.localizacao.nome}"></a>
              </c:forEach>
           </c:if>
         </div>
         <div id="more-photo">
            <span><a href="#"><fmt:message key="ver.todos"/></a></span>
         </div>
       </div>
       
       <div class="lista-corpo" id="borda">
           <span class="title"><fmt:message key="amigos" /> (${qtdAmigos})</span>
       </div>
       
       <c:if test="${not empty amigos }">
        <div id="list-friends">
         <c:forEach var="amigo" items="${amigos}" varStatus="i">
             <div class="friend">
                <c:if test="${empty amigo.id.usuarioAmigo.fotoPerfil}">
                  <a href="<c:url value="/perfil/${amigo.id.usuarioAmigo.id}/${amigo.id.usuarioAmigo.nomeFormat}" />"><img src="<c:url value="/img/no-photo-user-60x60.jpg" />" alt="${amigo.id.usuarioAmigo.nome}" title="${amigo.id.usuarioAmigo.nome}"/></a>
                </c:if>
                <c:if test="${not empty amigo.id.usuarioAmigo.fotoPerfil}">
                  <a href="<c:url value="/perfil/${amigo.id.usuarioAmigo.id}/${amigo.id.usuarioAmigo.nomeFormat}" />"><img src="<c:url value="/${amigo.id.usuarioAmigo.fotoPerfil }" />" alt="${amigo.id.usuarioAmigo.nome}" title="${amigo.id.usuarioAmigo.nome}" width="60" height="60"/></a>
                </c:if>
             </div>
         </c:forEach>
        </div>
       </c:if>
       
       <div id="more-friend">
          <span><a href="<c:url value="/perfil/amigos/${usuario.id}/${usuario.nomeFormat}"/>"><fmt:message key="ver.todos"/></a></span>
       </div>
</div>
    