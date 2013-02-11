function limpa(obj){
	obj.value = "";
}

function blocTexto(obj, qtd, objContador) {
	quant = qtd;
	total = obj.value.length;
	
	if(total <= quant){
		resto = quant - total;
		objContador.value = resto;
	}
	else{
		obj.value = obj.value.substr(0,quant);
	}
}

function onEnter(evt){
	var key_code = evt.keyCode ? evt.keyCode :
		     evt.charCode ? evt.charCode :
		    	 evt.which ? evt.which : void 0;
	
	if(key_code ==13){
		return true;
	}
}
