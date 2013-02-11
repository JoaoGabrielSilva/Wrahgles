 $(document).ready(function(){
	 $('#file').change(function(){
		var contexto = "";
		if(window.location.pathname.search("Wrahgles") != -1){
			contexto = "/Wrahgles";
		}
		$('#cadastro-pessoal').attr('action', contexto + '/usuario/previewAvatar');
		$('#cadastro-pessoal').submit();
		 
	 }); 
 });