<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Gantt View</title>
	
	<script type="text/javascript" src="../jquery/jquery-3.5.1.js"></script>
	<link rel="stylesheet" type="text/css" href="../jquery-ui/jquery-ui.css">
	<script type="text/javascript" src="../jquery-ui/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css" href="../ganttView/jquery.ganttView.css">
	<script src="../ganttView/jquery.ganttView.js"></script>
	<script type="text/javascript" src="../ganttView/date.js"></script>
	
	<script type="text/javascript" src="../ganttView/data.js"></script>
	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>
	
	
</head>
<body>
	<h1>Gantt View</h1>
	
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
	
</body>

<script type="text/javascript">
	$(function () {
		
		$.get("ganttView", function(response) {
			
			var ganttData = JSON.parse(response);
			
			$("#ganttChart").ganttView({
				data: ganttData,
				slideWidth: 600,
				behavior: {
					onClick: function (data) {
						var msg = "You clicked on an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onResize: function (data) {
						var msg = "You resized an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
						
// 						console.log(data);
						
						$.post(
							"ganttView",
							{
								id: data.id,
								name: data.name,
								start: data.start.toString("yyyy-MM-dd"),
								end: data.end.toString("yyyy-MM-dd"),
								idProjeto: data.idProjeto,
								color: data.color
							}
						).fail(function(response) {
					    	alert( response.responseText );
				  		});
						
					},
					onDrag: function (data) {
						var msg = "You dragged an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
						
						$.post(
							"ganttView",
							{
								id: data.id,
								name: data.name,
								start: data.start.toString("yyyy-MM-dd"),
								end: data.end.toString("yyyy-MM-dd"),
								idProjeto: data.idProjeto,
								color: data.color
							}
						).fail(function(response) {
					    	alert( response.responseText );
				  		});
					}
				}
			});
			// $("#ganttChart").ganttView("setSlideWidth", 600);
		});
		
	});
</script>

</html>