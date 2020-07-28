<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>File upload</title>
	<script type="text/javascript" src="../jquery/jquery-3.5.1.js"></script>
</head>
<body>
	<h3>File upload</h3>
	
	<input type="file" id="file" name="file" onchange="uploadFile();">
	<img alt="Imagem" src="" id="target" width="200" height="200">
	<br>
	<br>
	<input type="button" id="dwn-btn" value="Download file"/>
	
</body>

<script type="text/javascript">
	function uploadFile() {
		var target = document.querySelector("img");
// 		var targetDownload = document.querySelector("a");
		var file = document.querySelector("input[type=file]").files[0];
		var reader = new FileReader();
		
		reader.onloadend = function (){
			target.src = reader.result;
// 			targetDownload.href = reader.result;
			
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
	
	function download(content, filename) {
	    var element = document.createElement('a');
	    element.setAttribute('href', content);
	    element.setAttribute('download', filename);

	    element.style.display = 'none';
	    document.body.appendChild(element);

	    element.click();

	    document.body.removeChild(element);
	}
	
	// Start file download.
	document.getElementById("dwn-btn").addEventListener("click", function(){
	    // Generate download of hello.txt file with some content
	    var content = $("img").attr("src");
	    var filename = $("#file").val().split("\\")[2];
	    
	    if (!content && !filename) {
	    	alert("There isn't image for download");
	    } else {
		    download(content, filename);
	    }
	    
	}, false);
</script>

</html>