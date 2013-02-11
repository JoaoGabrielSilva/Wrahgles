$(document).ready(function() {
	if($("#effect-success").val() != null)    	
		$("#effect-success").show().fadeOut(10000);
});

function callBusca() {
	var contexto = "";
	if(window.location.pathname.search("Wrahgles") != -1) {
		contexto = "/Wrahgles";
	}

	try{
		if(map != null) {
	    	document.frmBusca.action = contexto + "/busca/" + map.getZoom();
		} else {
			document.frmBusca.action = contexto + "/busca/11";
		}
	} catch(e) {
		document.frmBusca.action = contexto + "/busca/11";
	}   	
    document.frmBusca.submit();
}

function clickEnterSubmitForm(e) {
    if(OnEnter(e)) {
    	callBusca();
        return false;
    } else {
        return true;
    }
}
