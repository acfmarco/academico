	// Desenvolvido pela Cobra Tecnologia, HÉLIO C. LIMA - 07-08-2012


	// Variável global utilizada para cofigurar o comportamento dos elementos _cabecalho_check_visivel, _cabecalho_check_editavel, _check_visivel e _check_editavel
	// se for true o evento impacta os elementos da direita se for false não impacta nada.
	// Valor default: true, 
	var varBoolEventoCabecalhoCompleto = true;
	
	var listaCheckboxColuna;
	// Função responsável por adicionar os eventos no carregamento da página.
	function f_adicionarEventos(idTabela){
		var dtParametros = $(idTabela);
		var listaCheckbox = dtParametros.find("input[type=checkbox]");
		listaCheckboxColuna		= new Array(19);
		
		var arrayTodosItensMarcados = new Array(19);
		for(var index = 0 ; index < arrayTodosItensMarcados.length ; index++){
			arrayTodosItensMarcados[index] = true;
			listaCheckboxColuna[index] = new Array(); // aproveitando o lop para preencher outro array (um array dentro de outro array para simular uma matriz)
		}
		
		var checkboxCorrente, indexColuna, idCheck, qtdCheckbox, indexLinha;
		qtdCheckbox = listaCheckbox.length;
		for(var index = 0 ; index < qtdCheckbox ; index++){
			checkboxCorrente = listaCheckbox.eq(index);			
			
			idCheck = checkboxCorrente.attr("id");
			indexColuna = idCheck.substring(idCheck.length - 2);
			indexColuna = parseInt(indexColuna, 10);
			
			if((idCheck.indexOf("_cabecalho_check_") != -1) || (idCheck.indexOf("_check_visivel") != -1) || (idCheck.indexOf("_check_editavel") != -1)){
				if(varBoolEventoCabecalhoCompleto){
					checkboxCorrente.change(function(){
						f_eventoCheckBox($(this),true);
					});
				}else{
					checkboxCorrente.change(function(){
						f_eventoCheckBox($(this),false);
					});					
				}				
			}
			
			if(listaCheckboxColuna[indexColuna][0] == null){
				indexLinha = 0;
			}else{
				indexLinha = listaCheckboxColuna[indexColuna].length;
			}
			
			listaCheckboxColuna[indexColuna][indexLinha] = checkboxCorrente; 
			if(arrayTodosItensMarcados[indexColuna]){
				if(! checkboxCorrente.is(':disabled') && ! checkboxCorrente.is(':checked') && indexLinha != 0){
					arrayTodosItensMarcados[indexColuna] = false;
				}
			}
			
		}
		
		//Marcando os checkbox de cabeçalhos, se toda a coluna estiver marcada
		var varListaCheckCabecalhos = $(idTabela).find("input[id*=_cabecalho_check]");
		var idCheck, indexColuna; 
		varListaCheckCabecalhos.each(function(){
			if(! $(this).is(':disabled')){
				idCheck = $(this).attr("id");
				indexColuna = idCheck.substring(idCheck.length - 2);
				indexColuna = parseInt(indexColuna, 10);
				$(this).attr("checked", arrayTodosItensMarcados[indexColuna]);	
			}			
		});
		
		// habilitando e desabilitando valores no onload da página.
		for(indexColuna = 0 ; indexColuna < listaCheckboxColuna.length ; indexColuna++){
			for(indexLinha = 0 ; indexLinha < listaCheckboxColuna[indexColuna].length ; indexLinha++){
				if( ! listaCheckboxColuna[indexColuna][indexLinha].is(':disabled')){
					idCheck = listaCheckboxColuna[indexColuna][indexLinha].attr("id");
					if(varBoolEventoCabecalhoCompleto){
						if(idCheck.indexOf("_check_visivel") != -1){
							f_eventoVisivel(indexColuna, indexLinha);
						}
						if(idCheck.indexOf("_check_editavel") != -1){
							f_eventoEditavel(indexColuna, indexLinha);
						}
					}						
				}				
			}
		}
	}
	
	function f_eventoCheckBox(objCheckboxEvento, paramBoolEventoCabecalhoCompleto){
		idCheck = objCheckboxEvento.attr("id");
		indexColuna = idCheck.substring(idCheck.length - 2);
		indexColuna = parseInt(indexColuna, 10);
		if(idCheck.indexOf("_cabecalho_check_") != -1){ // Se for cabeçalho
			var varValorCabecalho = objCheckboxEvento.is(':checked');
			for(var index = 0; index < listaCheckboxColuna[indexColuna].length ; index++){
				if( ! listaCheckboxColuna[indexColuna][index].is(':disabled')){
					listaCheckboxColuna[indexColuna][index].attr("checked", varValorCabecalho);
					if(paramBoolEventoCabecalhoCompleto){
						if(idCheck.indexOf("_cabecalho_check_visivel") != -1 || idCheck.indexOf("_cabecalho_check_editavel") != -1){
							if(varValorCabecalho){
								listaCheckboxColuna[indexColuna + 1][index].attr("disabled",false);
							}else{
								listaCheckboxColuna[indexColuna + 1][index].attr("checked",false);
								listaCheckboxColuna[indexColuna + 1][index].attr("disabled",true);
								if(idCheck.indexOf("_cabecalho_check_visivel") != -1){
									listaCheckboxColuna[indexColuna + 2][index].attr("checked",false);						
									listaCheckboxColuna[indexColuna + 2][index].attr("disabled",true);
								}
							}						
						}	
					}
				}			
			}			
		}else{
			if(paramBoolEventoCabecalhoCompleto){
				if(idCheck.indexOf("_check_visivel") != -1){
					var varIdEditavel = objCheckboxEvento.attr("id").replace("_check_visivel","_check_editavel");
					varIdEditavel = varIdEditavel.substring(0,(varIdEditavel.length - 2));
					var varIdObrigatorio = objCheckboxEvento.attr("id").replace("_check_visivel","_check_obrigatorio");
					varIdObrigatorio = varIdObrigatorio.substring(0,(varIdObrigatorio.length - 2));
					var trElemento = objCheckboxEvento.closest('tr');
					var objEditavel = trElemento.find("input[id*='"+varIdEditavel+"']").eq(0);			
					var objObrigatorio = trElemento.find("input[id*='"+varIdObrigatorio+"']").eq(0);
					if(objCheckboxEvento.is(':checked')){
						objEditavel.attr("disabled",false);
					}else{
						objEditavel.attr("checked",false);
						objEditavel.attr("disabled",true);

						objObrigatorio.attr("checked",false);
						objObrigatorio.attr("disabled",true);
					}				
				}
				
				if(idCheck.indexOf("_check_editavel") != -1){
					var varIdObrigatorio = objCheckboxEvento.attr("id").replace("_check_editavel","_check_obrigatorio");
					varIdObrigatorio = varIdObrigatorio.substring(0,(varIdObrigatorio.length - 2));
					var objObrigatorio = objCheckboxEvento.closest('tr').find("input[id*='"+varIdObrigatorio+"']").eq(0);
					if(objCheckboxEvento.is(':checked')){
						objObrigatorio.attr("disabled",false);
					}else{
						objObrigatorio.attr("checked",false);
						objObrigatorio.attr("disabled",true);
					}
				}				
			}
		}
	}
	
	function f_eventoVisivel(indexColuna, indexLinha){
		var objEventoVisivel = listaCheckboxColuna[indexColuna][indexLinha];
		if(objEventoVisivel.is(':checked')){
			listaCheckboxColuna[indexColuna + 1][indexLinha].attr("disabled",false);
		}else{
			listaCheckboxColuna[indexColuna + 1][indexLinha].attr("checked",false);
			listaCheckboxColuna[indexColuna + 1][indexLinha].attr("disabled",true);			
			listaCheckboxColuna[indexColuna + 2][indexLinha].attr("checked",false);						
			listaCheckboxColuna[indexColuna + 2][indexLinha].attr("disabled",true);			
		}
	}
	
	function f_eventoEditavel(indexColuna, indexLinha){
		var objEventoEditavel = listaCheckboxColuna[indexColuna][indexLinha];
		if(objEventoEditavel.is(':checked')){
			listaCheckboxColuna[indexColuna + 1][indexLinha].attr("disabled",false);
		}else{
			listaCheckboxColuna[indexColuna + 1][indexLinha].attr("checked",false);
			listaCheckboxColuna[indexColuna + 1][indexLinha].attr("disabled",true);			
		}
	}