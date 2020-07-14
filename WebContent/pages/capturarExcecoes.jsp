<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
</head>
<body>
	<h3>Capturar exceções com jquery</h3>
	
	<input type="text" id="txtValor" value="informar valor">
	<input type="button" value="Testar Exceção" onclick="testarExcecao();">
</body>

<script type="text/javascript">
	function testarExcecao() {
// 		alert($('#txtValor').val());
		var valorInformado = $('#txtValor').val();
		
		$.ajax({
			method: "POST",
			url: "capturarExcecao",//para qual servlet?
			data: {"valorParam": valorInformado}
		}).done(function(response) {//resposta ok - nenhum erro
			alert("success");
		}).fail(function(xhr, status) {//resposta erro - algum problema ocorreu
			alert("Erro: " + xhr.responseText);
// 		}).always(function(response) {
// 			alert("complete");
		});
	}
</script>

</html>