/*As quatro funções abaixo estão designadas apenas para verificar se uma string corresponde a uma data válida.
recomenda-se não expandir suas funções. por Hélio Lima.*/
var erroData = ""
function isDate(mascara,data){  
  var dia,mes,ano;
  var indexOfDia,indexOfMes,indexOfAno;
  var ultimoDiaMes = new Array();
  ultimoDiaMes[0] = 0; //não utilizado
  ultimoDiaMes[1] = 31;
  ultimoDiaMes[2] = 28; //Em um trecho de código mais adiante esse valor passará a ser 29, caso o ano da data seja um Ano Bissexto
  ultimoDiaMes[3] = 31;
  ultimoDiaMes[4] = 30;
  ultimoDiaMes[5] = 31;
  ultimoDiaMes[6] = 30;  
  ultimoDiaMes[7] = 31;
  ultimoDiaMes[8] = 31;
  ultimoDiaMes[9] = 30;
  ultimoDiaMes[10] = 31;
  ultimoDiaMes[11] = 30;
  ultimoDiaMes[12] = 31;
  
  indexOfDia = mascara.indexOf("dd");
  if(indexOfDia == -1){
	erroData = "erro 01 - A mascara de data é inválida - 'dd' não foi encontrado!! (Informe isto ao administrador do sistema)";
	return false;
  }    
      
  indexOfMes = mascara.indexOf("mm");
  if(indexOfMes == -1){
	erroData = "erro 02 - A mascara de data é inválida - 'mm' não foi encontrado!! (Informe isto ao administrador do sistema)";
	return false;
  }   
      
  indexOfAno = mascara.indexOf("aaaa");
  if(indexOfMes == -1){
	erroData = "erro 03 - A mascara de data é inválida - 'aaaa' não foi encontrado!! (Informe isto ao administrador do sistema)";
	return false;
  }    
        
  if(mascara.length != data.length){
	erroData = "erro 04 - Formato de data invalido!! formato padrão : " + mascara;
	return false;
  }    
            
  for(var i=0;i<mascara.length;i++){
    if(mascara.charAt(i) == 'd' || mascara.charAt(i) == 'm' || mascara.charAt(i) == 'a'){
      if(!(data.charAt(i) >= '0' && data.charAt(i) <= '9')){
		erroData = "erro 05 - Formato de data invalido!! formato padrão : "+mascara;
		return false;
	  }
        
    }else{
      if(mascara.charAt(i) != data.charAt(i)){
		erroData = "erro 06 - Formato de data invalido!! formato padrão : "+mascara;
		return false;
	  }             
    }
  }
  dia = trimNum(data.substring(indexOfDia,(indexOfDia+2)));
  mes = trimNum(data.substring(indexOfMes,(indexOfMes+2)));
  ano = trimNum(data.substring(indexOfAno,(indexOfAno+4))); 
  
  dia = parseInt(dia);
  mes = parseInt(mes);
  ano = parseInt(ano);
  if(isAnoBisexto(ano))
    ultimoDiaMes[2] = 29;
  
  if(!(mes >= 1 && mes <=12)){
	erroData = "O mês informado (" + mes + ") não é valido!";
	return false;
  }
    
  if(!(dia >= 1 && dia <= ultimoDiaMes[mes])){
	erroData = "O dia (" + dia + ") não corresponde ao mês especificado!";
	return false;
  }    
  erroData = "";
  return true;
}

/*Retorna a descrição do erro que ocorreu na função isDate*/
/*Recomenda-se chamar essa função somente após a chamada da função isDate informar que uma data é inválida.*/
function getErroData(){
	return erroData;
}

function isAnoBisexto(valAno){
  if((valAno%4) == 0 && ((valAno%100)!= 0 || (valAno%400) == 0)){
	return true;
  }else{
	return false;
  }
}

//remove os zeros a esquerda de um número
function trimNum(num){
  while(num.length > 0){
	if(num.charAt(0) == '0'){
		num = num.substring(1);
	}else{
		break;
	}
  }
  return num;
}

function getMyDate(mascara,data){  
  var indexOfDia,indexOfMes,indexOfAno,data;
  if (isDate(mascara,data)){
		indexOfDia = mascara.indexOf("dd");
		indexOfMes = mascara.indexOf("mm");
		indexOfAno = mascara.indexOf("aaaa");

		dia = trimNum(data.substring(indexOfDia,(indexOfDia+2)));
		mes = trimNum(data.substring(indexOfMes,(indexOfMes+2)));
		ano = trimNum(data.substring(indexOfAno,(indexOfAno+4))); 

		data = new Date(ano,mes,dia);
		return data;
  }else{
	alert("Erro na Função: getMyDate! (Informe isto ao administrador do sistema)");
	return null;
  }
}

function getValorData(mascara,data){  
  var indexOfDia,indexOfMes,indexOfAno,valor_data;
  if (isDate(mascara,data)){
		indexOfDia = mascara.indexOf("dd");
		indexOfMes = mascara.indexOf("mm");
		indexOfAno = mascara.indexOf("aaaa");

		dia = data.substring(indexOfDia,(indexOfDia+2));
		mes = data.substring(indexOfMes,(indexOfMes+2));
		ano = data.substring(indexOfAno,(indexOfAno+4));
		
		valor_data = ano + mes + dia;		
		return valor_data;
  }else{
	alert("Erro na Função: getMyDate! (Informe isto ao administrador do sistema)");
	return null;
  }
}

function comparacaoVerdadeira(data_1,operador,data_2){
	var mascara = "dd/mm/aaaa";
	if (isDate(mascara,data_1) && isDate(mascara,data_2)) {
		if (operador == ">"){
			if (getValorData(mascara,data_1) > getValorData(mascara,data_2)){
				return true;
			}else{
				return false;
			}
		}
		if (operador == "<"){
			if (getValorData(mascara,data_1) < getValorData(mascara,data_2)){
				return true;
			}else{
				return false;
			}
		}
		if (operador == "=="){
			if (getValorData(mascara,data_1) == getValorData(mascara,data_2)){
				return true;
			}else{
				return false;
			}
		}
		if (operador == ">="){
			if (getValorData(mascara,data_1) >= getValorData(mascara,data_2)){
				return true;
			}else{
				return false;
			}
		}
		if (operador == "<="){
			if (getValorData(mascara,data_1) <= getValorData(mascara,data_2)){
				return true;
			}else{
				return false;
			}
		}
	}else{
		alert("Erro na função comparacaoVerdadeira!! Parâmetros incorretos!! (Informe isto ao administrador do sistema)");
		return false;
	}
	return false;
}