	var map;
    var start;
    var mapMarkers = [];
    var mapHtml = [];
    var zoom = 15;
    var icoLocalDefault = {primaryColor: "#836FFF", strokeColor: "#000000", labelColor: "#000000"};
    var icoLocalSelect = {primaryColor: "#FFFF00", strokeColor: "#000000", labelColor: "#000000"};
    
    function load(modalLogin) {
  
    	var nomeMapa;
    	if(document.getElementById("mapa") != null) {
    		nomeMapa = "mapa";
    		ajustaDivs();
    	}	
    	if(document.getElementById("mapaFormLocalizacao") != null) {
    		nomeMapa = "mapaFormLocalizacao";
    	}	
    	
		// Verificando compatibilidade do Browser com a API do Google Maps
		if (GBrowserIsCompatible()) {
	        var opts = { resultList : G_GOOGLEBAR_RESULT_LIST_SUPPRESS, suppressInitialResultSelection : true, showOnLoad: true};
	        
			// Criando o mapa e adicionando-o na DIV que ir� exibi-lo na p�gina
			map = new GMap2(document.getElementById(nomeMapa), {googleBarOptions: opts});
	        
			// Adicionando controles do Mapa, como op��es de SAT�LITE e bot�es de Zoom
			if(nomeMapa == "mapa") {
				map.addControl(new GLargeMapControl());
				map.addControl(new GMapTypeControl());
			} else {
				map.addControl(new GSmallMapControl());
			}	
				
			// Adicionando a localiza��o inicial do mapa
			start = new GLatLng(-23.549982, -46.638594);
	        map.setCenter(start, zoom);
		}
		
		if(modalLogin == 'open') {
			$('#login-form').dialog('open');
		}	
    }
	
	// Aloca a fun��o AjustaDivs no resize do windows
    $(window).resize(function() { ajustaDivs() });
	
	function ajustaDivs() {
		if(document.getElementById("mapa") != null) {
			var altDisp = document.documentElement.clientHeight;
			var largDisp = document.documentElement.clientWidth;
			
			if((largDisp - 330) > 700) {
				document.getElementById("mapa").style.width = (largDisp - 330) + "px";
			} else {
				document.getElementById("mapa").style.width = "700px";
			}	
			
			if((altDisp - 178) > 350) {
				document.getElementById("mapa").style.height = (altDisp - 178) + "px";
			} else {
				document.getElementById("mapa").style.height = "350px";
			}	
		}
	}
