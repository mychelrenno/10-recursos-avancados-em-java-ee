<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="../jquery/jquery-3.5.1.js"></script>
</head>
<body>
	<h1>Página pai load jquery</h1>
	<input type="button" value="Carregar página" onclick="carregar();">
	
<!-- 	local de carregamento da pagina filha -->
	<div id="mostrarPaginaFilha"></div>
	
</body>

<script type="text/javascript">
	function carregar() {
		$("#mostrarPaginaFilha").load('paginaFilha.jsp');//load pagina em jquery
	}
</script>

</html>