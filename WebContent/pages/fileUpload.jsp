<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>File upload</title>
	<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
</head>
<body>
	<h3>File upload</h3>
	
	<input type="file" id="file" name="file" onchange="uploadFile();">
	<img alt="Imagem" src="" id="target" width="200" height="200">
	
</body>

<script type="text/javascript">
	function uploadFile() {
		var target = document.querySelector("img");
		var file = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();
		
		reader.onloadend = function (){
			target.src = reader.result;
			
			$.ajax({
				method: "POST",
				url: "fileUpload",
				data: {"fileUpload": reader.result}
			}).done(function(response) {
				alert("Successo: " + response);
			}).fail(function(xhr, status) {
				alert("Erro: " + xhr.responseText);
			});
		};
		
		if (file) {
			reader.readAsDataURL(file);
		} else {
			target.src = "";
		}
	}
</script>

</html>