<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Barra de progresso</title>
	
	<link rel="stylesheet" href="../jquery-ui/jquery-ui.css">
	<script type="text/javascript" src="../jquery/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="../jquery-ui/jquery-ui.js"></script>
	
	<style type="text/css">
		#myProgress {
			width: 100%;
			background-color: #ddd;
		}
		#myBar {
			width: 1%;
			height: 30px;
			background-color: #4caf50;
		}
		
		.ui-progressbar {
			position: relative;
		}
		.progress-label {
			position: absolute;
 			left: 50%;
			top: 4px;
			font-weigth: bold;
			text-shadow: 1px 1px 0 #fff;
		}
	</style>
	
</head>
<body>
	<h1>Exemplo com javascript</h1>
	
	<div id="myProgress">
		<div id="myBar"></div>
	</div>
	<br>
	<button onclick="exibirBarra()">Iniciar a barra de progresso</button>
	
	<br>
	
	<h1>Barra de progresso com jquery</h1>
	<div id="progressbar">
		<div class="progress-label">Carregando...</div>
	</div>
	
</body>

<script type="text/javascript">
	//script da barra de progresso por jquery
	$(function(){
		var progressbar = $("#progressbar"), progresslabel = $(".progress-label");
		
		//cria a barra de progresso no div
		progressbar.progressbar({
      		value: false,
			change: function() {
				progresslabel.text( progressbar.progressbar( "value" ) + "%" );
			},
			complete: function() {
				progresslabel.text( "Complete!" );
			}
	    });
		
		function progress(){
			var val = progressbar.progressbar("value") || 0;
			progressbar.progressbar("value", val + 2);
			if (val < 99) {
				setTimeout(progress, 80);
			}
		}
		setTimeout(progress, 2000);//chamado ao abrir a tela
	});

	//script da barra de progresso por javascript
	function exibirBarra(){
		var elem = document.getElementById("myBar");
		var width = 1;
		var id = setInterval(frame, 10);
		
		function frame(){
			if (width >= 100) {
				clearInterval(id);
			} else {
				width++;
				elem.style.width = width + "%";
			}
		}
	}
</script>

</html>