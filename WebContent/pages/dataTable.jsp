<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DataTable JQuery</title>

<script type="text/javascript" src="../jquery/jquery-3.5.1.js"></script>

<link rel="stylesheet" type="text/css" href="../dataTables/datatables.css"/>
<script type="text/javascript" src="../dataTables/datatables.js"></script>

</head>
<body>
	<table id="minhaTabela" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>id</th>
				<th>login</th>
<!-- 				<th>Office</th> -->
<!-- 				<th>Age</th> -->
<!-- 				<th>Start date</th> -->
<!-- 				<th>Salary</th> -->
			</tr>
		</thead>
		<!-- <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
		<tbody>
			<tr>
				<td>Tiger Nixon</td>
				<td>System Architect</td>
				<td>Edinburgh</td>
				<td>61</td>
				<td>2011/04/25</td>
				<td>$320,800</td>
			</tr>
			<tr>
				<td>Garrett Winters</td>
				<td>Accountant</td>
				<td>Tokyo</td>
				<td>63</td>
				<td>2011/07/25</td>
				<td>$170,750</td>
			</tr>
			<tr>
				<td>Ashton Cox</td>
				<td>Junior Technical Author</td>
				<td>San Francisco</td>
				<td>66</td>
				<td>2009/01/12</td>
				<td>$86,000</td>
			</tr>
		</tbody> -->
	</table>
</body>

<script type="text/javascript">
	$(document).ready(function() {// faz o processamento na hora que abre a tela
// 	    $('#minhaTabela').DataTable();
		$('#minhaTabela').DataTable({
	        "processing": true,
	        "serverSide": true,
// 	        "order": [[ 1, "desc" ]],
	        "ajax": "carregarDadosDataTable"// URL de retorno dos dados do servidor (Resposta do servidor)
// 	        initComplete: function() {
// 		      $('#minhaTabela_processing').remove()
// 		    },
// 		    serverParams: function() {
// 		      $('#minhaTabela_processing').remove()
// 				console.log("oi");
// 		    },
// 		    stateLoadParams: function() {
// 		      $('#minhaTabela_processing').remove()
// 		    },
// 		    onProcessing: function() {
// 		      $('#minhaTabela_processing').remove()
// 		    }
	    });
	});
</script>

</html>