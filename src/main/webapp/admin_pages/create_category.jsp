<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
   	<meta charset="UTF-8">
    <title>Creazione categoria</title>
</head>
<body>

	<%@include file = "../fragments/admin_menu.jsp"%>
	<%@include file = "../fragments/admin_header.jsp"%>
		
<div class = "create_ctg">
	<div class = "form-content">
		<form id="CategoryForm" action=<%= request.getContextPath() + "/RegistrationCategoryControl" %>>
		<h2>Registra la categoria</h2>
		<label for="name">Nome categoria:</label>
			<input type="text"
		         id="name"
		         name="name"
		         placeholder="Nome Categoria">
		   <br>
		  <label for="description">Descrizione:</label><br>
		  <textarea class="form-control" 
		         id="description"
		         name="description"
		         ></textarea>
		  <br>
		       
		 <button class = "s-btn2" type="submit" value="Registra indirizzo"> Crea nuova categoria</button>
		<label for="id"></label>
		</form>
	</div>
</div>
<script>

	$(document).ready(function() {
		
		
		$("#CategoryForm").submit(function(){
					$.ajax({
						async: true,
						url: "CategoryControl",
						type: "POST",
						datatType: "json",
						data: $("#CategoryForm").serialize(),
						success: function(data){
							var checkboxes = document.createElement('input');
							var paragraph = document.createElement('p');
							checkboxes.type = "checkbox";
						
							$("#displayResult").append(paragraph);
							$("#displayResult").append(checkboxes);
						}
					});
				
	});

	

	function sendData() {
		
	}
});
</script>
</body>
</html>