<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div id="col-esq-wrahgles">
      <div class="propaganda">
      </div>
    
    
    <div class="list-corpo">
        <span class="title">+ Comentados</span>
        <div class="list-info">
        
         <c:forEach var="mCom" items="${maisComentados}" varStatus="i">
           <div>
              <span class="order-list">${i.count}</span>
              <a href="<c:url value="/local/${mCom.local.id}/${mCom.local.nomeFormat}" />"><span>${mCom.local.nome}</span></a>
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
          <a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}" />"><img src="<c:url value="/${mUsers.usuario.fotoPerfil}" />" alt="${mUsers.usuario.nome}" title="${mUsers.usuario.nome}" style='max-width: 45px; max-height: 45px;' /></a>
       </c:if>
       
       <div class="info-list-high">
            <span><a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}" />">${mUsers.usuario.nome} (${mUsers.qtdComentarios} opiniões)</a></span>
            <span>Última: <a href="<c:url value="/local/${mUsers.ultimoComentario.localizacao.id}/${mUsers.ultimoComentario.localizacao.nomeFormat}" />">${mUsers.ultimoComentario.localizacao.nome}</a></span>
        </div>
       </div>
       </c:forEach>
    
    </div>
    
    <div class="lista-corpo"> 
      <span class="title">Maiores contribuidores</span>
      
      <c:forEach var="mUsers" items="${usuarioMaisComentarios}">
       <div class="list-high">
       <c:if test="${empty mUsers.usuario.fotoPerfil}">
							<a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}" />"><img src="<c:url value="/img/no-photo-user-45X45.jpg" />" alt="${mUsers.usuario.nome}" title="${mUsers.usuario.nome}"/></a>
						</c:if>
						<c:if test="${not empty mUsers.usuario.fotoPerfil}">
							<a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}" />"><img src="<c:url value="/${mUsers.usuario.fotoPerfil}" />" alt="${mUsers.usuario.nome}" title="${mUsers.usuario.nome}" style='max-width: 45px; max-height: 45px;' /></a>
						</c:if>
                    
                        <div class="info-list-high">
                            <!-- <span><a href="#">Developer</a></span> -->
                            <span><a href="<c:url value="/perfil/${mUsers.usuario.id}/${mUsers.usuario.nomeFormat}" />">${mUsers.usuario.nome} (${mUsers.qtdComentarios} opiniões)</a></span>
                            <span>Última: <a href="<c:url value="/local/${mUsers.ultimoComentario.localizacao.id}/${mUsers.ultimoComentario.localizacao.nomeFormat}" />">${mUsers.ultimoComentario.localizacao.nome}</a></span>
                        </div>
                     </div>
       </c:forEach>
       </div>
       
      
     <div class="lista-corpo">
                    <span class="title"> Novos integrantes Loucaliza</span>
                    
                    	<c:forEach var="us" items="${newUsers}">
							<div class="list-high">
								<c:if test="${empty us.fotoPerfil}">
									<a href="<c:url value="/perfil/${us.id}/${us.nomeFormat}" />"><img src="<c:url value="/img/no-photo-user-45X45.jpg" />" alt="${us.nome}" title="${us.nome}"/></a>
								</c:if>
								<c:if test="${not empty us.fotoPerfil}">
									<a href="<c:url value="/perfil/${us.id}/${us.nomeFormat}" />"><img src="<c:url value="/${us.fotoPerfil}" />" alt="${us.nome}" title="${us.nome}" style='max-width: 45px; max-height: 45px;' /></a>
								</c:if>
							
		                        <div class="info-list-high">
		                            <!-- <span><a href="#">Developer</a></span> -->
		                            <span><a href="<c:url value="/perfil/${us.id}/${us.nomeFormat}" />">${us.nome}</a></span>
		                            <!-- <span><a href="#">Brasil</a></span> -->
		                        </div>
		                    </div>
						</c:forEach>
						                                                      
                </div>                                    
            </div>
    