<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Creazione categoria</title>
</head>
<body>
		<form id="CategoryForm" action=<%= request.getContextPath() + "/RegistrationCategoryControl" %>>
		<h1>Registra la categoria</h1>
		<label for="name">Nome categoria:</label>
			<input type="text"
		         class="form-control" 
		         id="name"
		         name="name"
		         placeholder="Nome Categoria">
		   <br>
		  <label for="description">Descrizione:</label><br>
		  <textarea class="form-control" 
		         id="description"
		         name="description"
		         placeholder="Descrizione."></textarea>
		  <br>
		       
		 <button type="submit" value="Registra indirizzo"> Crea nuova categorie</button>
		<label for="id"></label>

	
	</form>
	
		
	
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