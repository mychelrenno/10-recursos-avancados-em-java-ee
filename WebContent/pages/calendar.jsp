<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Calendar</title>
	
	<link rel="stylesheet" type="text/css" href="../fullcalendar/main.min.css">
	<script src="../fullcalendar/main.min.js"></script>
	
	<script type="text/javascript" src="../jquery/jquery-3.5.1.js"></script>
	
</head>
<body>
	<h1>Calendar</h1>
	<div id="calendar" style="width:80%; height: 80%"></div>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		$.get("calendar", function(response) {
			
			var jsonData = JSON.parse(response);
			
			var calendar = new FullCalendar.Calendar(calendarEl, {
				headerToolbar : {
					left : 'prev,next today',
					center : 'title',
					right : 'dayGridMonth,dayGridWeek,timeGridDay'
				},
				initialView : 'dayGridMonth',
// 				navLinks: true,
				editable : true,
// 				eventLimit: true,
				events : jsonData
			});
			calendar.render();
		});

	});
</script>

</html>