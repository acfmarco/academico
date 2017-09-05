/**
 * 
 */
function validarData(data){
	
	if (data != null && data.value != "") {

		var situacao = "true"; 
		var dia = data.value.substring(0,2); 
	    var mes = data.value.substring(3,5); 
	    var ano = data.value.substring(6,10);

	    // verifica se a data comtem o tamanho do formato de data: dd/mm/aaaa
	    if(data.value.length != "dd/mm/aaaa".length){
	    	situacao = "false";
	    }
	
	    // verifica o dia valido para cada mes 
	    if ((dia < 01)||(dia < 01 || dia > 30) && (  mes == 04 || mes == 06 || mes == 09 || mes == 11 ) || dia > 31) { 
	        situacao = "false"; 
	    } 
	
	    // verifica se o mes e valido 
	    if (mes < 01 || mes > 12 ) { 
	        situacao = "false"; 
	    } 
	
	    // verifica se e ano bissexto 
	    if (mes == 2 && ( dia < 01 || dia > 29 || ( dia > 28 && (parseInt(ano / 4) != ano / 4)))) { 
	        situacao = "false"; 
	    } 
	
	    if (situacao == "false") { 
	        alert("A data informada não é válida!"); 
	        data.value = "";
	        return false;
	    } 
	    
	    return true;
    
	}
    return true;
}

function validarDataLayoutFormulario(data){
	
	if (data != null && data.value != "") {

		var situacao = "true"; 
		var dia = data.value.substring(0,2); 
	    var mes = data.value.substring(3,5); 
	    var ano = data.value.substring(6,10); 
	
	    // verifica o dia valido para cada mes 
	    if ((dia < 01)||(dia < 01 || dia > 30) && (  mes == 04 || mes == 06 || mes == 09 || mes == 11 ) || dia > 31) { 
	        situacao = "false"; 
	    } 
	
	    // verifica se o mes e valido 
	    if (mes < 01 || mes > 12 ) { 
	        situacao = "false"; 
	    } 
	
	    // verifica se e ano bissexto 
	    if (mes == 2 && ( dia < 01 || dia > 29 || ( dia > 28 && (parseInt(ano / 4) != ano / 4)))) { 
	        situacao = "false"; 
	    } 
	
	    if (situacao == "false") { 
	        return false;
	    } 
	    
	    return true;
    
	}
    return true;
}

/**
 * Função para substituir caractesres especiais.
 * @param {str} campo
 */
function substituiAcento(obj) {

	var specialChars = [
		{val:"a",let:"áàãâä"},
		{val:"e",let:"éèêë"},
		{val:"i",let:"íìîï"},
		{val:"o",let:"óòõôö"},
		{val:"u",let:"úùûü"},
		{val:"c",let:"ç"},
		{val:"A",let:"ÁÀÃÂÄ"},
		{val:"E",let:"ÉÈÊË"},
		{val:"I",let:"ÍÌÎÏ"},
		{val:"O",let:"ÓÒÕÔÖ"},
		{val:"U",let:"ÚÙÛÜ"},
		{val:"C",let:"Ç"},
		{val:"",let:"?!()"}
	];

	var regex;
	var returnString = obj.value;
	for (var i = 0; i < specialChars.length; i++) {
		regex = new RegExp("["+specialChars[i].let+"]", "g");
		returnString = returnString.replace(regex, specialChars[i].val);
		regex = null;
	}
	obj.value =  returnString;
}

function converteMoedaFloat(valor){
      
      if(valor === ""){
         valor =  0;
      }else{
         valor = valor.replace(".","");
         valor = valor.replace(",",".");
         valor = parseFloat(valor);
      }
      return valor;

   }
   
function ValidaCampo(t){
					
	if (converteMoedaFloat(t.value) > 1000){
	
		if (confirm('O valor cadastrado no tipo de cálculo está superior a 1.000,00. Deseja prosseguir?')){
			return true;
		}else{ 
			t.value = "0,00";
			return false;
		}			
		
	}else{
		return true;
	}
}

/**
 * Função para deixar o valor do campo em caixa alta.
 * @param {str} campo
 */
function caixaAlta(obj) {
	obj.value = obj.value.toUpperCase();
}
/**
 * Retira caracteres especiais
 * @param obj
 */
function retiraCarectesEspeciais(obj){
	obj.value = obj.value.replace(/[^ _a-zA-Z0-9]/g,"");
}

/**
 * Retira caracteres especiais menos a barra
 * @param obj
 */
function retiraCarectesEspeciaisComBarra(obj){
	obj.value = obj.value.replace(/[^ \/_a-zA-Z0-9]/g,"");
}


/**
 * Função para deixar o valor do campo em caixa alta e sem acento.
 * @param {str} campo
 */
function campoSemAcentoECaixaAlta(obj) {
	substituiAcento(obj);
	caixaAlta(obj);
	retiraCarectesEspeciais(obj);
}


/**
 * Função para deixar o valor do campo em caixa alta e sem acento, mas permite a barra '/'.
 * @param {str} campo
 */
 
function campoSemAcentoECaixaAltaComBarra(obj){
	substituiAcento(obj);
	caixaAlta(obj);
	retiraCarectesEspeciaisComBarra(obj);
}


function atualizarTabelaCriticas(campo){
	
	if (campo.checked) {
		$("#tipoSelecionado").val("S");
	} else {
		$("#tipoSelecionado").val("N");
	}
	rcmdSelecao();
}