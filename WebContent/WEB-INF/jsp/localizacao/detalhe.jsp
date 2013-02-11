<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${localizacao.nome}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="url" content="http://www.wrahgles.com.br/local/${localizacao.id}/${localizacao.nomeFormat}" />
<meta name="title" content="${localizacao.nome}" />
<meta name="keys" content="${localizacao.descricao}" />
<meta name="keywords" content="${localizacao.descricao}" />
<meta name="description" content="${localizacao.descricao}" />
<meta name="abstract" content="${localizacao.descricao}" />

<meta name="google-site-verification" content="QeV-cZPQkppinYbnmmZUY6R3zn7foOYxMPykuCN9jfU" />

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/js/jquery/themes/base/jquery.ui.all.css" />" type="text/css"  />
<link rel="stylesheet" href="<c:url value="/js/jquery/demos/demos.css" />" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/modal.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/stylesStarRating.css" />" type="text/css" />

<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/puts.js" />" ></script>
<script src="http://maps.google.com/maps?file=api&v=2&key=ABQIAAAAVKZqRPxOAI4OXh0BZW3iMRTKS6Hug5pktf2Ljq3jiV5sDEEpxxSetFD_Bp_aU-VHhy2c7C0EQA5KQg" type="text/javascript"></script>
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
<script type="text/javascript" src="<c:url value="/js/localizacao/modalDenuncia.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/localizacao/detalhe.js" />" ></script>
<script type="text/javascript" src="<c:url value="/js/star-rating.js" />" ></script>

<c:if test="${not empty usuarioLogado.usuario}">
<script type="text/javascript">
  $(document).ready(function(){
	  if($('#notaComentarioUsuario').val() != ''){
		  setvoto($('#notaComentarioUsuario').val(),'nota');
		  showvoto('nota');
		  $('#countDesc').attr('value', $('#countDesc').val() - $('#comentario').val().length);
	  }
	  
	  $('#show').toggle(
		function(){
			$('div#box-comment-text').fadeIn();
		},
		function(){
			$('div#box-comment-text').fadeOut();
		}
	  );
	 
	  $("#show-photo").toggle(
			  function(){
				  $("div#box-photo").fadIn();
			  },
			  function(){
				  $("div#box-photo").fadIn();
			  }
			  
	  );
	  
	  $('#file').change(function(){
		  $('#frmFoto').submit();
	  });
	  
	  $("#tabs").tabs();
	  $("#accordion").accordion({
		  collapsible:true
	  });
	  
  });
</script>
</c:if>
<c:if test="${empty usuarioLogado.usuario}">
 <script type="text/javascript">
   $(document).ready(function(){
	  $("#show").click(
			  function(){
				  var contexto = "";
				  if(window.location.pathname.search("Wrahgles") != -1){
					  contexto = "/Wrahgles";
				  }
				  $('#frmComent').attr('action', contexto + '/comentario/form/' + $('#codLocalizacao').val());
				  $('#frmComent').submit();
			  });
	   
	  $("#tabs").tabs();
	    $("#accordion").accordion({
	    	collapsible:true
	    });
   });
 </script>
</c:if>

<c:if test="${not empty paginaComentario}">
<script type="text/javascript">
  $(document).ready(function(){
     $("div#box-omment-text").fadIn(); //exibir div
  
  });
</script>
</c:if>

</head>

<body onload="${modal == 'denuncia' ? 'openDenuncia' : ''}">
<input type="hidden" id="codLocalizacao" value="${localizacao.id}"/>
<input type="hidden" id="notaComentarioUsuario" value="${comentarioUsuario.nota}"/>
<input type="hidden" id="latitude" value="${localizacao.latitude}"/>
<input type="hidden" id="longitude" value="${localizacao.longitude}"/>
<input type="hidden" id="localEstado" value="${localizacao.estado.sigla}"/>
<input type="hidden" id="localPais" value="${localizacao.estado.pais.sigla}"/>

<%@ include file="../../../menu.jsp"  %>

<%@ include file="modalDenuncia.jsp"  %>


<div id="geral">
         <a name="client-name"></a>
         <div id="col-esq-comentario">
          <h3>${localizacao.nome}</h3>
          <div id="global-comments">
             <c:if test="${not empty localFoto}">
              <div id="img-loc">
                 <img src="<c:url value="/${localFoto.imagem}"/>" style="max-width: 128px; max-height:128px;">
                 <a href="<c:url value="/local/fotos/${localizacao.id }/${localizacao.nomeFormat }" />"><span><fmt:message key="ver.fotos"/></span></a>
              </div>
              </c:if>
              
              <div id="comments">
                  <img src="<c:url value="/images/notas/grandes/${nota}.png"/>" alt="">
                  <div>
                     <span id="size-comments"><strong>(${qtdComentarios}) <fmt:message key="comentario.detalhe"/></strong></span>
                  </div>
              </div>
              <div id="description-client">
                  <p>
                   <span class="type-references"><fmt:message key="endereco.detalhe"></fmt:message></span>
              	   <span>${localizacao.endereco}</span>
                  </p>
                  
                  <c:if test="${not empty localizacao.complemento}">
                  <p>
                   <span class="type-references"><fmt:message key="complemento.detalhe"/></span>
                   <span>${localizacao.complemento}</span>
                  </p>
                  </c:if>
                  
                  <p>
                     <span class="type-references"><fmt:message key="servicos.detalhe" /></span>
                     <span>${localizacao.descricao}</span>
                  </p>
                  <c:if test="${not empty localizacao.email}">
                    <p>
                       <span class="type-references"><fmt:message key="email.localizacao"/></span>
                       <span>${localizacao.email}</span>
                    </p>
                  </c:if>
                  
                  <c:if test="${not empty localizacao.site}">
                    <p>
                      <span class="type-references"><fmt:message key="site.oficial.detalhe"/></span>
                      <span><a href="${localizacao.site}" target="_blank">${localizacao.site}</a></span>
                    </p>
                  </c:if>
              </div>
              
              <div id="box-comment">
              <div id="details">
                <form action="" id="frmComment">
                 <a href="#" id="show"><span id="comment-user"></span></a>
                 <a href="<c:url value="/denuncia/form/${localizacao.id}"/>"><span id="false-location"></span></a>
                 <c:if test="${not empty usuarioLogado}"><a href="#" id="show-photo"><span id="photo-upload"></span> </a></c:if>
                </form>
              </div>
              
              <!-- USUARIO COMENTARIO -->
              <form action="<c:url value="/comentario/salvar/${localizacao.id}"/>" name="frmComentario" id="frmComentario" method="post">
               <div id="box-comment-text">
                 <div id="danota" onmouseout="showvoto('nota');">
                   <table border="0" cellspacing="0" cellpadding="0">
                     <tr>
                       <td width="180"><img alt="" src="<c:url value="/images/b.gif"/>">  </td>
                       <td valign="middle"><span id="notatxt" style="font-weight: bold;"><span style="color: #f16825"> &laquo; <fmt:message  key="nota.desejada.detalhe"/></span></span> </td>
                     </tr>
                   </table>
                   <div class="some"><img src="<c:url value="/images/off-med.png" />" /><img src="<c:url value="/images/on-med.png" />" /><img src="<c:url value="/images/b.gif" />" width="1" height="1" id="nota00" />
                   <input name="txtnota" type="hidden" id="txtnota" value="Clique na nota;fraco;regular;bom;ótimo;excelente"/>
                   </div>
                   <input name="comentario.nota" type="hidden" id="notaval" value="0"/>
                   <script type="text/javascript">showvoto('nota');</script>
                   
                   <div id="photo-client">
                    <c:if test="${empty usuarioLogado.usuario.fotoPerfil }">
                      <img src="<c:url value="/img/no-photo-user-60x60.jpg" />" alt="" title=""/>
                    </c:if>
                    <c:if test="${not empty usuarioLogado.usuario.fotoPerfil}">
                    <img src="<c:url value="/${usuarioLogado.usuario.fotoPerfil}" />" alt="" title="" width="60" />
                    </c:if>
                   </div>
                    <div id="box-text">
                        	<textarea name="comentario.comentario" id="comentario" onkeyup="javascript:blocTexto(this, 1000, document.frmComentario.countDesc)">${comentarioUsuario.comentario}</textarea>
                        	<span><input type="text" name="countDesc" id="countDesc" size="5" value="1000" disabled="disabled"></span>
							<span>
                                <input type="submit" name="" value="Comentar" id="send-c" />
							</span>
                    </div>
               </div>
               </form>
               
                 <div id="box-photo">
                	<p><fmt:message key="selecione.foto"/></p>
                    <form name="frmFoto" id="frmFoto" action="<c:url value="/localizacao/sendPicture/${localizacao.id}" />" enctype="multipart/form-data" method="post">
                    	<input type="file" name="file" id="file"  />
                        <!-- <input type="submit" name="" value="Enviar" id="send-c" /> -->
                    </form>
                </div>
                <div>
                 </div>
               <div id="tabs">
                 <ul>
                        <li><a href="#tabs-1"><fmt:message key="comentario"/> (${qtdComentarios})</a></li>
                        <li><a href="#tabs-2"><fmt:message key="fotos"/> (${qtdFotos})</a></li>
                        <li><a href="#tabs-3"><fmt:message key="mapa"/></a></li>
                        <li><a href="#tabs-4"><fmt:message key="visitantes"/> (${qtdVisitantes})</a></li>
                        <li><a href="#tabs-5"><fmt:message key="vizinho"/> (0)</a></li>
                 </ul>
                 <div id="tabs-1">
                    <div id="posts-comments">
                       <c:if test="${empty comentarios}"></c:if>
                    </div>
                    
                    
                 
                 </div>
               
               
               </div>
              
              
              </div>
              
          
          
          
          
          </div>
         
         </div>




</div>








</body>
</html>