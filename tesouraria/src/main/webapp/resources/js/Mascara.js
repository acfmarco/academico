/*
*       Script: Mascaras em Javascript
*       Autor:  Cobra Tecnologia
*       Data:   14/03/2012
*       Obs:    
*/
        /*Função Pai de Mascaras*/
        function Mascara(o,f){
                v_obj=o;
                v_fun=f;
                setTimeout("execmascara()",1);
        }
        
        /*Função que Executa os objetos*/
        function execmascara(){
    		var valAntigo = v_obj.value;
    		var valComMacara = v_fun(v_obj.value);
    		// Esse if impede que o valor seja setado sem ter sido modificado.
    		// Exemplo: seta esquerda, seta direita , Ctrl+Shift+Home fazia com que o cursor fosse deslocado para o final do valor.
    		if(valComMacara != valAntigo){
    			v_obj.value= valComMacara;
    		}
        }
        
        /*Função que Determina as express�es regulares dos objetos*/
        function leech(v){
                v=v.replace(/o/gi,"0");
                v=v.replace(/i/gi,"1");
                v=v.replace(/z/gi,"2");
                v=v.replace(/e/gi,"3");
                v=v.replace(/a/gi,"4");
                v=v.replace(/s/gi,"5");
                v=v.replace(/t/gi,"7");
                return v;
        }
        
        /*Função que permite apenas numeros*/
        function Integer(v){
                return v.replace(/\D/g,"");
        }
        
        /*Função que padroniza telefone (11) 4184-1241*/
        function Telefone(v){
                v=v.replace(/\D/g,"");                            
                v=v.replace(/^(\d\d)(\d)/g,"($1) $2") ;
                v=v.replace(/(\d{4})(\d)/,"$1-$2")   ;   
                return v;
        }
        
        /*Função que padroniza telefone 4184-1241*/
        function TelefoneSemDDD(v){
        	v=v.replace(/\D/g, "");          
        	if(v.length<9) {
        		v=v.replace(/^(\d{4})(\d)/,"$1-$2");
        	} else {
        		v=v.replace(/^(\d{5})(\d)/,"$1-$2");
        	}
            return v;
        }
        
        /*Função que padroniza telefone (11) 41841241*/
        function TelefoneCall(v){
                v=v.replace(/\D/g,"");                   
                v=v.replace(/^(\d\d)(\d)/g,"($1) $2");  
                return v;
        }
        
        /*Função que padroniza CPF*/
        function Cpf(v){
                v=v.replace(/\D/g,"");                                   
                v=v.replace(/(\d{3})(\d)/,"$1.$2");         
                v=v.replace(/(\d{3})(\d)/,"$1.$2");         
                                                                                                 
                v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
 
                return v;
        }
        
        /*Função que padroniza CEP*/
        function Cep(v){
       
        		v=v.replace(/\D/g,"");                             
                v=v.replace(/^(\d{5})(\d)/,"$1-$2"); 
                return v;
        }
        
        /*Função que padroniza CNPJ*/
        function Cnpj(v){
                v=v.replace(/\D/g,"");                              
                v=v.replace(/^(\d{2})(\d)/,"$1.$2");      
                v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3"); 
                v=v.replace(/\.(\d{3})(\d)/,".$1/$2");              
                v=v.replace(/(\d{4})(\d)/,"$1-$2") ;                       
                return v;
        }
        
        /*Função que permite apenas numeros Romanos*/
        function Romanos(v){
                v=v.toUpperCase();                        
                v=v.replace(/[^IVXLCDM]/g,""); 
                
                while(v.replace(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/,"")!="")
                        v=v.replace(/.$/,"");
                return v;
        }
        
        /*Função que padroniza o Site*/
        function Site(v){
                v=v.replace(/^http:\/\/?/,"");
                dominio=v;
                caminho="";
                if(v.indexOf("/")>-1)
                        dominio=v.split("/")[0];
                        caminho=v.replace(/[^\/]*/,"");
                        dominio=dominio.replace(/[^\w\.\+-:@]/g,"");
                        caminho=caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g,"");
                        caminho=caminho.replace(/([\?&])=/,"$1");
                if(caminho!="")dominio=dominio.replace(/\.+$/,"");
                        v="http://"+dominio+caminho;
                return v;
        }

        /*Função que padroniza DATA*/
        function Data(v){
                v=v.replace(/\D/g,"") ;
                v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
                v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
                return v;
        }
        
        /*Função que padroniza DATA MM/yyyy*/
        function DataMesEAno(v){
                v=v.replace(/\D/g,"") ;
                v=v.replace(/(\d{2})(\d)/,"$1/$2"); 
                return v;
        }
        
        /*Função que padroniza DATA*/
        function Hora(v){
                v=v.replace(/\D/g,"") ;
                v=v.replace(/(\d{2})(\d)/,"$1:$2")  ;
                return v;
        }
        
        /*Função que padroniza valor mon�tario*/
        function Valor(v){
        	
        	v = v.replace(/\D/g,"");  //permite digitar apenas números 
			v = v.replace(/[0-9]{15}/,"inválido") ;  //limita pra máximo 999.999.999.999,99
			v = v.replace(/(\d{1})(\d{11})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos
			v = v.replace(/(\d{1})(\d{8})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos 
			v = v.replace(/(\d{1})(\d{5})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos 
			v = v.replace(/(\d{1})(\d{1,2})$/,"$1,$2");        //coloca virgula antes dos últimos 2 digitos 
        	v.value = v; 
			return v;

        }
        
        /*Função que padroniza valor mon�tario*/
        function ValorFolha(v){
        	
        	v = v.replace(/\D/g,"");  //permite digitar apenas números 
			v = v.replace(/[0-9]{17}/,"inválido") ;  //limita pra máximo 999.999.999.999,99
			v = v.replace(/(\d{1})(\d{14})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos
			v = v.replace(/(\d{1})(\d{11})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos 
			v = v.replace(/(\d{1})(\d{8})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos 
			v = v.replace(/(\d{1})(\d{5})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos
			v = v.replace(/(\d{1})(\d{1,2})$/,"$1,$2");        //coloca virgula antes dos últimos 2 digitos 
        	v.value = v; 
			return v;

        }
        
        /*Função que padroniza Area*/
        function Area(v){
                v=v.replace(/\D/g,"") ;
                v=v.replace(/(\d)(\d{2})$/,"$1.$2"); 
                return v;
                
        }        

        /*Função que padroniza número da agência*/
        function NumeroAgencia(v){
	    	
	    	v=v.replace(/[^0-9]/g,"");
	    	v=v.replace(/(\w{4})(\w)/,"$1-$2");
	    	
            return v;
        }
        
        /*Função que padroniza número conta corrente*/
        function NumeroContaCorrente(v){
        	
        	v=v.replace(/[^xX_0-9]/g,"");
        	
    		if(v.length>2) {
    			tam = v.length-1;
        		dinamica = new RegExp('(\\w{'+tam+'})(\\w)',"g");
        		
        		v=v.replace(dinamica,"$1-$2");
    		}
	    	
            return v;
        }
        /* Função que  padroniza campo alfa numérico*/
        function AlfaNumerico(v){
        	return v.replace(/[^a-zA-Z0-9]/g,"").toUpperCase();
        }
        
        /* Função que  padroniza campo matricula com zeros a esquerda*/
        function AlfaNumericoMatricula(v){          	
        	var zeros = "0";
        	
        	        	    
        	    while (v.length < 9)
        	        v = zeros + v;
        	
        	
        	return v;
        }
        
        