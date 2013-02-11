$(document).ready(function(){
	var contexto = "";
	if(window.location.pathname.search("Wrahgles") != -1){
		contexto = "/Wrahgles";
	}
	
	$("#cidade-local").autocomplete(contexto + "/localizacao/cidade/listJSON/" + $('#idEstado').val(), {
		dataType: "json", //pra falar que vamos tratar um json
		parse:function(json) {//para tratar o json
			return $.map(json.list,
					function(cidade){
				   return {
					   data: cidade, //todos os dados do produto 
					   value:cidade.nome, // o valor logico do produto
					   result:cidade.nome // o que vai aparece ao selecionar
				   };
				
			});
		},
		formatItem: function(cidade){ // o que vai aparecer na lista de autocomplete
			return cidade.nome;
		}
	});
	
});