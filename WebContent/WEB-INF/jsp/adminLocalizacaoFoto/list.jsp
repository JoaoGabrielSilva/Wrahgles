<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wrahgles - Administrador</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<!-- Default -->
<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
<link rel="stylesheet" href="<c:url value="/css/stylesAdm.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/pagination.css" />" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.4.2.js" />"></script> 
<script type="text/javascript" src="<c:url value="/js/jquery.pagination.js" />"></script>

<script type="text/javascript"> 
var optPagination = {callback: pageselectCallback, items_per_page:20,
		num_display_entries:10,
		current_page:0,
		num_edge_entries:2,
		link_to:"#",
		prev_text:"Anterior",
		next_text:"Próximo",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true}; 
            
        function pageselectCallback(page_index, jq){
        	var contexto = "";
    		if(window.location.pathname.search("Wrahgles") != -1) {
    			contexto = "/Wrahgles";
    		}
            
             // Get number of elements per pagionation page from form
             var items_per_page = optPagination.items_per_page;

             /* Logica para realizar iteração de acordo com a pagina */
             var url = contexto + '/admin/localizacaoFoto/pagination/'+items_per_page+'/'+page_index;
             if($('#submenu').val() == 'aprovado') {
				url += '/true'; 
             } else if($('#submenu').val() == 'pendente') {
            	 url += '/false';
             }        

             $.ajaxSetup({cache: false});
             $.get(url, null, 
                     function(data) {
            	 		$.ajaxSetup({cache: true});
            	 		$('#Searchresult').html(data);    		
             		 }, 'html');
             
             return false;
        }

    	$(document).ready(function(){
			$("#paginacao").pagination($('#qtdLocalizacoes').val(), optPagination);
		});
</script>
</head>
<body>

<%@ include file="../../../menuAdm.jsp"  %>


<input type="hidden" id="qtdLocalizacoes" value="${qtdLocalizacoes}" />
<input type="hidden" id="submenu" value="${submenu}"> 


<table width="100%">
   <tr>
      <td align="left" height="50px">
         <table align="left" cellpadding="2px" cellspacing="2px">
          <tr>
             
             <!-- menu pagina todas -->
            <c:if test="${submenu eq 'todas'}">
               <td width="130" align="center">
                   <span class="label">
                     <b>Todas</b>
                   </span>
               </td>
               
               <td width="10" align="center"><span class="label">|</span></td>
               
            <td width="130" align="center">
		            	<span class="label">
		            	<a href="<c:url value="/admin/localizacaoFoto/listAprovado" />">Aprovadas</a>
		            	</span>
		            </td>
		            
		            <td width="10" align="center"><span class="label">|</span></td>
		            
		            <td width="130" align="center">
		            	<span class="label">
		            	<a href="<c:url value="/admin/localizacaoFoto/listPendente" />">Pendentes</a>
		            	</span>
		            </td>
		    	</c:if>
		    	
		    	<!-- *** MENU PÁGINA APROVADO *** -->
		    	<c:if test="${submenu eq 'aprovado'}">
		    		<td width="130" align="center">
		        		<span class="label">
		        		<a href="<c:url value="/admin/localizacaoFoto/list" />">Todas</a>
		        		</span>		        		
		        	</td>
		        	
		        	<td width="10" align="center"><span class="label">|</span></td>
		        	
		        	<td width="130" align="center">
		            	<span class="label">
		            	<b>Aprovadas</b>
		            	</span>
		            </td>
		            
		            <td width="10" align="center"><span class="label">|</span></td>
		            
		            <td width="130" align="center">
		            	<span class="label">
		            	<a href="<c:url value="/admin/localizacaoFoto/listPendente" />">Pendentes</a>
		            	</span>
		            </td>
		    	</c:if>
		    	
		    	<!-- *** MENU PÁGINA PENDENTE *** -->
		    	<c:if test="${submenu eq 'pendente'}">
		    		<td width="130" align="center">
		        		<span class="label">
		        		<a href="<c:url value="/admin/localizacaoFoto/list" />">Todas</a>
		        		</span>		        		
		        	</td>
		        	
		        	<td width="10" align="center"><span class="label">|</span></td>
		        	
		        	<td width="130" align="center">
		            	<span class="label">
		            	<a href="<c:url value="/admin/localizacaoFoto/listAprovado" />">Aprovadas</a>
		            	</span>
		            </td>
		            
		            <td width="10" align="center"><span class="label">|</span></td>
		            
		            <td width="130" align="center">
		            	<span class="label">
		            	<b>Pendentes</b>
		            	</span>
		            </td>
		    	</c:if>	
		        </tr>
		    	</table>
			</td>
		</tr>
	    
	    <tr>
	        <td align="left">
	        	<h2>${submenu == 'todas' ? 'Todas:' : submenu == 'aprovado' ? 'Aprovadas:' : submenu == 'pendente' ? 'Pendentes:' : '' }</h2>
	        </td>
	    </tr>
	    
	    <tr>
	        <td align="left">
	        	<div id="paginacao" class="pagination"></div>
	        </td>
	    </tr>
	    
	    <tr>
	        <td align="left">
	        	<div id="Searchresult"></div>
	        </td>
	    </tr>
</table>

<%@ include file="../../../footerAdm.jsp" %>