	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<!-- Mensagem -->
	
	<c:if test="${not empty mensagem }">
	 <div id="mensagem" style="display: none;">
	 <table align="center" width="100%" style="background-color: #191970">
	    <tr>
	        <td width="100%" align="center">
	            <span class="mensagem">${mensagem}</span>
	        </td>
	    </tr>
	 </table>
	 </div>
	</c:if>
	
	<!-- usuariologado -->
	<c:if test="${not empty usuarioLogado.usuario}">
	   <table align="right" cellpadding="2px" cellspacing="2px">
	     <tr>
	        <td><span class="size">${usuarioLogado.usuario.email}</span></td>
	        <td width="10" align="center"><span class="label">|</span></td>
	        <td width="70" align="center"><span class="label"><a href="<c:url value="/usuario/logoff" />">Sair</a></span></td>
	     </tr>
	   </table>
	</c:if>
	<br/><br/>
	
	<!-- Menu Acima -->
	<div id="styleone">
	   <ul>
	    <li><a href="<c:url value="/admin" />" class="${menu == null || menu == 'home' ? 'current': ''}">Home</a></li> 
	    <li><a href="<c:url value="/admin/usuario/list" />" class="${menu == 'usuario' ? 'current': ''}">Usuários</a></li>
	    <li><a href="<c:url value="/admin/localizacao/list" />" class="${menu == 'localizacao' ? 'current': ''}">Locais</a></li>
	    <li><a href="<c:url value="/admin/localizacao/enderecoDuplicado" />" class="${menu == 'enderecoDuplicado' ? 'current': ''}">Endereços Duplicados</a></li>
	    <li><a href="<c:url value="/admin/localizacaoFoto/list" />" class="${menu == 'localizacaoFoto' ? 'current': ''}">Local Foto</a></li>
	    <li><a href="<c:url value="/admin/comentario/list" />" class="${menu == 'comentario' ? 'current': ''}">Comentários</a></li>
	    <li><a href="<c:url value="/admin/denuncia/list" />" class="${menu == 'denuncia' ? 'current': ''}">Denúncia</a></li>
	    <li><a href="<c:url value="/admin/categoria/list" />" class="${menu == 'categoria' ? 'current': ''}">Categoria</a></li>
	    <li><a href="<c:url value="/admin/subcategoria/list" />" class="${menu == 'subcategoria' ? 'current': ''}">SubCategoria</a></li>
	    <li><a href="<c:url value="/admin/notificacao/form" />" class="${menu == 'notificacao' ? 'current': ''}">Notificar Clientes</a></li>
	    <li><a href="<c:url value="/admin/convite/list" />" class="${menu == 'convite' ? 'current': ''}">Enviar Convites</a></li>
	   </ul>	
	</div>
	
	<!-- logo e busca principal -->
	<table>
  <tr>
    <td>
      <form id="frmBusca" name="frmBusca" action="<c:url value="/busca/"/>">
             <table align="left" cellpadding="0px" cellspacing="0px" width="1010px">
               <tr>
                  <td width="305px"><h1><a href="<c:url value="/" />"><img src="<c:url value="/images/logo.jpg"/>"/></a></h1></td>
                   <td width="355px" valign="middle"></td> 
                   <td width="270px" valign="middle"></td>
                   <td width="30px" valign="middle"></td>
               </tr>
             </table>
      </form>
    </td>
  </tr>
<tr>
<td>

	
	