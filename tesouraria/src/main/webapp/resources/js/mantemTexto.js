function ocultarConfirmDialogExclusaoTexto(){
	$(document).find("div[id*=confirmDialogExclusaoTexto]").hide();
}

function ocultarConfirmDialogExclusaoDeclaracao(){
	$(document).find("div[id*=confirmDialogExclusaoDeclaracao]").hide();
}

function f_executarEventoSelectTexto(){
	if($('#nomeFuncaoOnSelectTexto').val().length > 0){
		setTimeout($('#nomeFuncaoOnSelectTexto').val(),500);	
	}		
}