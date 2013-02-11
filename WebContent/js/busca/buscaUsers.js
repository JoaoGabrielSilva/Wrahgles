var optPaginationBuscaUsers = {callback: pageselectCallbackBuscaUsers, items_per_page:20,
		num_display_entries:10,
		current_page:0,
		num_edge_entries:2,
		link_to:"#",
		prev_text:"<<",
		next_text:">>",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true}; 
            
        function pageselectCallbackBuscaUsers(page_index, jq){
        	var contexto = "";
    		if(window.location.pathname.search("Wrahgles") != -1) {
    			contexto = "/Wrahgles";
    		}
            
             // Get number of elements per pagionation page from form
             var items_per_page = optPaginationBuscaUsers.items_per_page;
             
             var qtd = $('#qtdUsuarios').val();
         	 if(qtd < items_per_page) {
         		 document.getElementById("paginacao").style.display = 'none';
         	 }
             
             $.ajaxSetup({cache: false});
             $.get(contexto + '/busca/paginationUsers/'+items_per_page+'/'+page_index+'?busca='+$("#name").val(), null, 
                     function(data) {
            	 		$.ajaxSetup({cache: true});
            	 		$('#Searchresult').html(data);    		
             		 }, 'html');
             
             return false;
        }

    	$(document).ready(function(){
			$("#paginacao").pagination($('#qtdUsuarios').val(), optPaginationBuscaUsers);
		});