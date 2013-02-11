var mapLocal;
var start;
var markMapa;
var infoWindowCadastroLocal = null;

function carregaMapaCadastroLocalizacao(){
	var myOptions = {mapTypeId:google.maps.MapTypeID.ROADMAP};
	mapLocal = new google.maps.Map(document.getElementById("mapa"), myOptions);
	
	mapLocal.setCenter(new google.maps.LatLng(-23.549982, -46.638594));
	mapLocal.setZoom(15);
	
	if(document.getElementById("latitude").value.length > 0
			&& document.getElementById("longitude").value.length > 0){
		
		pesquisarLocalizacao();
	}
	
}

function pesquisarLocalizacao(){
	var html = "";
	var nome = $("#nome").val();
	var origem = $("#end").val();
	var compl = $("#complemento").val();
	var telefone = $("#telefone").val();
	var site = $("#site").val();
	var desc =$("#description").val();
	var estado = $("#estado-local").val();
	var cidade = ("#cidade-local").val();
	
	//concatena cidade e estado no endereco
	
	if(cidade.length > 0)
		origem += ' - ' + cidade;
	if(estado.length > 0)
		origem += ' - ' + estado;
	
	var list = {id:0, nome: nome, endereco: origem, complemento: compl, telefone: telefone, site: site, descricao: desc};
	
	var geocoder = new google.maps.Geocoder();
	
	geocoder.geocode({'address': origem}, function(results, status){
	     if(status == google.maps.GocoderStatus.OK && $("#end").val().length > 0){
	    	 document.getElementById("latitude").value = results[0].geometry.location.lat();
	    	 document.getElementById("Longitude").value = results[0].geometry.location.lng();
	    	 
	    	 html = createHtmlInfoWindowCadastroLocal(list);
	    	 if(infoWindowCadastroLocal != null)
	    		 infoWindowCadastroLocal.close();
	    	 
	    	 infoWindowCadastroLocal = new google.maps.InfoWindow({
	    		 content: html,
	    		 maxEidth : 200
	    	 });
	    	 
	    	 if(markMapa != null)
	    	    markMapa.setMap(null);
	    	 
	    	 markMapa = new google.maps.Marker({position: results[0].geometry.location});
	    	 google.maps.event.addListener(markMapa, "click", function(){
	    		 infoWindowCadastroLocal.close();
	    		 infoWindowCadastroLocal.open(mapLocal, markMapa);
	    		 
	    	 });
	    	 
	    	 markMapa.setMap(mapLocal);
	    	 infoWindowCadastroLocal.open(mapLocal, markMapa);
	    	 mapLocal.setCenter(results[0].geometry.location);
	    	 mapLocal.setZoom(15);
	     }	else{
	    	 if(markMapa != null)
	    		 markMapa.setMap(null);
	    	 
	    	 if(infoWindowCadastroLocal != null)
	    		 infoWindowCadasdtroLocal.close();
	    	 
	    	 document.getElementById("latitude").value = "";
	    	 document.getElementById("longitude").value = "";
	    	 
	     }
	});
}

function createHtmlInfoWindowCadastroLocal(local){
	var html = "";
	html += "<table align='left'>";
	
	var contexto = "";
		if(window.location.pathname.search("Wrahgles") != -1) {
			contexto = "/Wrahgles";
		}
		
		html += "<tr><td align='left'><h1><a href='#'>" + local.nome + "</a></h1><br /></td></tr>";
		html += "<tr><td align='left'><span class='label'>" + local.endereco + "</span><br /></td></tr>";
		
		if(local.complemento.length > 0){
			html += "<tr><td align='left'><span class='label'>" + local.complemento + "</span><br /></td></tr>";
		}
		
		return html;
	
}

jQuery(function($){
	$("#telefone").mask("(99) 99999-9999");
});

$document.ready(function(){
  $("#estado-local").change(function(){
	var contexto = "";  
	if(window.location.pathname.search("Wrahgles") != -1){
		contexto = "/Wrahgles";
	}
	$.get(contexto + "/localizacao/cidade/" + $(this).val(), null,
			function(data){
		    $('#divCidade').html(data);
	}, 'html');
  });	
});

function excluirLocal(url){
	document.cadastro.action = url;
	document.cadastro.submit();
}

function buscaSubCategorias(obj){
	var contexto = "";
	if(window.location.pathname.search("Wrahgles") != -1){
		contexto = "/Wrahgles";
	}
	
	$.get(contexto + "/localizacao/categoria/"+obj.value, null,
			function(data){
		    $('#divSubCategoria').html(data);
	}, 	'html');
	
}


