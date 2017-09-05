//Javascript utilizado para manipular o timeout de sessão
//Depende da variável timeout, declara no templateBase.xhtml
//Criado por Hugo Milhomens 

		var timer;
		timer = setTimeout('showAlert()',timeout);
		function sessionReset(){
			clearTimeout(timer);
			timer = setTimeout('showAlert()',timeout);		    
		}
		function showAlert(){
			timeoutDlg.show();
			$("#timeoutDlg").show();
		}
		$(document).ready(function(){
			$("body").bind("ajaxComplete", function(e, xhr, settings){
				sessionReset();
			});
		});