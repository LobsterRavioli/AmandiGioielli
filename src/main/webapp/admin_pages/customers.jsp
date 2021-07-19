<%@page import="beans.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
   
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Ordini effettuati</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<%@include file="../fragments/admin_menu.jsp" %>
	<%@include file="../fragments/admin_header.jsp" %>
	
<div class = "main-content">
	<div class = "table-content">
		<h2>Clienti</h2>
			<table id = "myTable">
					<tr>
						<th style="width: 20%">Id utente</th>
						<th style="width: 30%">Email</th>
						<th style="width: 20%">Nome</th>
						<th style="width: 20%">Cognome</th>
						<th style="width: 50%">Phone</th>	
					</tr>
			</table>
		<br>
	</div>
</div>



<script>
	$(document).ready(function(){
		
		$.ajax({
			async: true,
			url: "AdminCostumerControl",
			type: "POST",
			datatType: "json",
			success: function(data)
			{
				$("#myTable").DataTable()
				{
				}
			}
					
			},
			timeout: 3000,
		});
</script>

</body>
</html>