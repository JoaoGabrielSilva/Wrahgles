<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cadastrar local</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="url" content="http://www.Wrahgles.com.br/contato/form" />
<meta name="title" content="Contato" />
<meta name="keys" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="keywords" content="busca, encontrar, locais, localizações, bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="description" content="O Wrahgles ajuda você a encontrar os locais e serviços que estão próximos de você. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />
<meta name="abstract" content="O Wrahgles ajuda você a encontrar os locais e serviços que estão próximos de você. Como bancos, bares, restaurantes, baladas, casa noturna, happy hour, shopping, club, lojas, salões de beleza, clínicas, hospitais, cinema, academia" />

<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/styles.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/base.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/autocomplete/jquery.autocomplete.css" />" rel="stylesheet"  />

<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.maskedinput-1.2.2.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/localizacao/form.js" />"  charset="utf-8"></script>

<!-- AUTOCOMPLETE -->
<script type="text/javascript" src="<c:url value="/js/jquery-latest.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/external/jquery.bgiframe-2.1.1.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/autocomplete/jquery.autocomplete.js" />" charset="utf-8"></script>

<link type="text/css" href="<c:url value="/js/jquery/demos/demos.css" />" rel="stylesheet" />


</head>
<body onload="carregaMapaCadastroLocalizacao()">

<%@ include file="../../../menu.jsp"  %>


<div id="content">

 <div id="col-esq-localizacao">
 
    <!-- add erros -->
    <c:if test="${not empty errors}">
      <c:forEach items="${errors}" var="error">
       <p><span class="campos">(<span  id="defined">*</span>) ${error.message}</span></p>
      </c:forEach>
    </c:if>
    <div id="redirect">
       <span id="destaque">Localizações</span>
       <span class="seta"></span>
       <span>Cadastro de local</span>
    </div>
    <h3><fmt:message key="preencha.localizacao.form"></fmt:message></h3>
    <p><fmt:message key="campos.com.localizacao"/><span id="defined">*</span><fmt:message key="campo.obrigatorio.localizacao"/></p>
    
    <form name="cadastro" id="cadastro-localidade" action="<c:url value="/localizacao/salvar"/>" method="post">
      <input type="hidden" name="localizacao.id" value="${localizacao.id}"/>
      <input type="hidden" name="localizacao.latitude" id="latitude" value="${localizacao.latitude}"/>
      <input type="hidden" name="localizacao.longitude" id="longitude" value="${localizacao.longitude }"/>
      
      <table>
         <tr>
        	 <td class="info"><fmt:message key="campo.nome.localizacao" /><span id="defined">*</span></td>
         	<td colspan="2" class="campo">
           		<input type="text" name="localizacao.nome" id="nome" maxlength="200" value="${localizacao.nome}" onchange="javascript:perquisarLocalizacao();">
         	</td>
         </tr>
         <tr>
           <td></td>
           <td colspan="2" class="info-ex"><fmt:message key="exemplo.nome.localizacao"/></td>
         </tr>
         
         <tr>
            <td class="info"><fmt:message key="categoria.localizacao" /><span id="defined">*</span></td>
            <td colspan="2" class="campo" valign="bottom">
               <select class="option" name="localizacao.categoria.categoria.id" id="categoria" onchange="buscaSubCategorias(this)">
                    <option selected="selected" value="">(selecionar categoria)</option>
                    <c:if test="${empty localizacao.categoria.categoria.id }">
                  	 	<c:forEach var="cat" items="${categorias}">
                        	 <option value="${cat.nome}"></option>
                     	</c:forEach>
                   </c:if>
                   
                   <c:if test="${not empty localizacao.categoria.categoria.id }">
	                   <c:forEach var="cat" items="${categorias}">
		                     <c:if test="${localizacao.categoria.categoria.id == cat.id }">
		                	       <option value="${cat.id}" selected="selected">${cat.nome} </option>
		                     </c:if>
		                     <c:if test="${localizacao.categoria.categoria.id != cat.id }">
		                    	   <option value="${cat.id}">${cat.nome}</option>
		                     </c:if>
		               </c:forEach>
                  </c:if>
               </select>
            </td>
         </tr>
         
         <tr>
          <td></td>
         </tr>
         
         <tr>
           <td class="info"></td>
           <td colspan="2" class="campo" valign="top">
             <div id="divSubCategoria">
                <c:if test="${empty subCategorias}">
                	<select class="option" name="localizacao.categoria.id" id="relCategoria">
                   		 <option selected="selected" value="">(selecionar subcategoria)</option>
                	</select>
                </c:if>
                 
                 <c:if test="${not empty subCategorias}">
	                   <select class="option" name="localizacao.categoria.id" id="relCategoria">
		                     <option selected="selected" value="">(selecionar subcategoria)</option>
		                     
		                     <c:if test="${empty localizacao.categoria.id}">
			                        <c:forEach var="subCat" items="${subCategorias}">
			                    	       <option value="${subCat.id}">${subCat.subCategoria.nome}</option>
			                        </c:forEach>
		                     </c:if>
		                     
		                     <c:if test="${not empty localizacao.categoria.id}">
			                        <c:forEach var="subCat" items="${subCategorias}">
				                            <c:if test="${localizacao.categoria.id == subCat.id}">
				                          	     <option value="${subCat.id}" selected="selected">${subCat.subCategoria.nome}</option>
				                            </c:if>
				                            <c:if test="${localizacao.categoria.id != subCat.id}">
				                             	  <option value="${subCat.id}">${subCat.subCategoria.nome}</option>
				                            </c:if>
				                    </c:forEach>
		                     </c:if>
	                 </select>
              </c:if>
             </div>
           </td>
         </tr>
         
         <tr>
            <td></td>
         </tr>
         
         <tr>
             <td class="info"><fmt:message key="endereco.localizacao"/><span id="defined">*</span></td>
             <td colspan="2" class="campo">
                <input type="text" name="localizacao.endereco" id="end" size="50" maxlength="250" value="${localizacao.endereco }"
                onchange="javascript:pesquisarLocalizacao();">
             </td>
         </tr>
         <tr>
         	<td></td>
           	<td colspan="2" class="info-ex"><fmt:message key="exemplo.endereco.localizacao"/></td>
         </tr>
         
         <tr>
            <td class="info"><fmt:message key="complemento.localizacao" /></td>
            <td colspan="2" class="campo">
               <input type="text" name="localizacao.complemento" id="complemento" maxlength="250" value="${localizacao.complemento }"
               onchange="javascript:pesquisarLocalizacao();">
            </td>
         </tr>
         <tr>
            <td></td>
            <td colspan="2" class="info-ex"><fmt:message key="exemplo.complemento.localizacao"/></td>
         </tr>
         <tr>
           <td class="info"><fmt:message key="estado.localizacao"/><span id="defined">*</span></td>
           <td colspan="2" class="campo">
               <select class="option" name="localizacao.estado.sigla" id="estado-local" onchange="javascript:pesquisarLocalizacao();">
                  <option selected="selected" value="">(selecionar estado)</option>
                  
                  <c:if test="${empty localizacao.estado.id}">
                    <c:forEach var="est" items="${estados}">
                       <option value="${est.sigla}">${est.nome}</option>
                    </c:forEach>
                  </c:if>
                  
                  <c:if test="${not empty localizacao.estado.id}">
                    <c:forEach var="est" items="${estados}">
                       <c:if test="${localizacao.estado.id == est.id}">
                     		<option value="${est.sigla}" selected="selected">${est.nome}</option>
                       </c:if>
                       <c:if test="${localizacao.estado.id != est.id}">
                     		<option value="${est.sigla}">${est.nome}</option>
                       </c:if>
                    </c:forEach>
                  </c:if>
               </select>
           </td>
         </tr>
         
         <tr>
           <td> </td>
         </tr>
         
          <tr>
            	<td></td>
                <td colspan="2" class="info-ex"></td>
          </tr>
          
          <tr>
            <td class="info"><fmt:message key="cidade.localizacao"/><span id="defined">*</span></td>
            <td colspan="2" class="campo">
                <div id="divCidade">
                      <input type="hidden" name="idEstado" id="idEstado" value="${localizacao.estado.id}"/>
                      <input type="text" id="cidade-local" name="localizacao.cidade" maxlength="150" value="${localizacao.nome}" onchange="javascript:pesquisarLocalizacao();">
                      <script type="text/javascript" src="<c:url value="/js/localizacao/autocomplete.cidade.js"/>" charset="utf-8"></script>
                </div>
            </td>
          </tr>
          <tr>
          	 <td></td>
          	 <td colspan="2" class="info-ex"></td>
          </tr>      
          
          <tr>
            <td class="info"><fmt:message key="telefone.localizacao" /></td>
            <td colspan="2" class="campo">
               <input type="text" name="localizacao.telefone" id="telefone" size="25"  value="${localizacao.telefone}"
               		onchange="javascript:pesquisarLocalizacao();">
            </td>
          </tr>
          <tr>
             <td></td>
             <td colspan="2" class="info-ex"><fmt:message key="exemplo.telefone.localizacao"/></td>
          </tr>
          
          <tr>
             <td class="info"><fmt:message key="email.localizacao"/></td>
             <td colspan="2" class="campo">
                <input type="text" name="localizacao.email" id="email-local" maxlength="250" value="${localizacao.email}" onchange="javascript:pesquisarLocalizacao();">
             </td>
          </tr>
          <tr>
               <td></td>
               <td colspan="2" class="info-ex"><span class="exemplo"><fmt:message key="exemplo.email.localizacao"/></span></td>
          </tr>
          <tr>
            <td class="info"><fmt:message key="site.localizacao"/></td>
            <td colspan="2" class="campo">
              <c:if test="${empty localizacao.site}">
                <input type="text" name="localizacao.site" id="site" size="50" maxlength="200" value="http://" onchange="javascript:pesquisarLocalizacao();">
              </c:if>
              	<c:if test="${not empty localizacao.site}">
			       	<input type="text" name="localizacao.site" id="site" size="50" maxlength="200" value="${localizacao.site }"
			       	onchange="javascript:pesquisarLocalizacao();"/>
			    </c:if>  
            </td>
          </tr>
          <tr>
              	<td></td>
                <td colspan="2" class="info-ex"><fmt:message key="exemplo.site.localizacao"/></td>
          </tr>
          <tr>
             <td class="info" valign="top"><fmt:message key="descricao.localizacao"/><span id="defined">*</span></td>
             <td class="campo">
                  	<textarea name="localizacao.descricao" id="description" rows="7" 
			       	onkeyup="javascript:blocTexto(this, 250, document.cadastro.contador)" onchange="javascript:pesquisarLocalizacao();" >${localizacao.descricao }</textarea>
             </td>
              <td valign="bottom">
                   <input type="text" name="contador" id="contador" size="4" value="250" disabled="disabled">
              </td>
          </tr>
          <tr>
              	<td></td>
                <td id="info-desc"><fmt:message key="obs.descricao.localizacao"/></td>
               <td></td>
          </tr>
          <c:if test="${usuarioLogado.usuario.role eq 'admin' and not empty localizacao.id}">
            <tr>
              <td class="info">
                 Statu: <span id="defined">*</span>
              </td>
              <td class="campo" colspan="2">
                <select name="localizacao.flgAtivo">
                   <c:if test="${localizacao.flgAtivo}">
                       <option value="true" selected="selected">Aprovada</option>
                       <option value="false">Pendente</option>
                   </c:if>
                    <c:if test="${not localizacao.flgAtivo}">
                       <option value="true">Aprovada</option>
                       <option value="false" selected="selected">Pendente</option>
                   </c:if>
                </select>
               </td>
            </tr>
          </c:if>
           <tr>
             	<td></td>
                   <td class="campo">
                    	<input type="submit" name="send" id="send" value="${localizacao.id == null ? 'Cadastrar' : 'Alterar' }" />
			        	<c:if test="${usuarioLogado.usuario.role eq 'admin' and not empty localizacao.id}">
			        	<input type="button" name="send" id="send" value="Excluir"  onclick="excluirLocal('<c:url value='/admin/localizacao/excluir/${localizacao.id}' />');"/>
			        	</c:if>
                    </td>
                    <td></td>
           </tr>
      </table>
    
    </form>
 </div>

<%@ include file="../../../footer.jsp" %>