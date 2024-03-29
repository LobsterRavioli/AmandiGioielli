<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

   	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Creazione prodotto</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation.js"></script>
</head>
<body>

<%@include file = "../fragments/admin_menu.jsp"%>
	<%@include file = "../fragments/admin_header.jsp"%>
		
<div class = "create_prd">
	<div class = "form2-content">
	<form action=<%= request.getContextPath() + "/RegistrationProductControl"%> enctype="multipart/form-data" method="POST">
			
				<input type="hidden" name="action" value="insert">
				
				<label for="name">Nome:</label><br>
				<input id="name" name="name" type="text" ><br>
				
				<label for="description">Descrizione Prodotto:</label><br>
				<textarea id="description" name="description" maxlength="100" rows="3"  ></textarea><br>
				
				<label for="description">Descrizione breve:</label><br>
				<textarea id="shortDescription" name="shortDescription" maxlength="100" rows="3"></textarea><br>
				

				
				<input id="price" name="price" type="number" min="0" placeholder = "Prezzo"><br>
				
				<input id="quantity" name="quantity" type="number" min="0" placeholder = "Quantità"><br>		
				
				<input id="discount" name="discount" type="number" min="0" placeholder = "Sconto" required><br>
				
				<input id="taxRate" name="taxRate" type="number" min="0"  placeholder = "Tax Rate" required><br>
				
				<div class = "load-f">
					<input class = "s-btn2" type="file" name="file" id="file" placeholder = "Scegli un file">
						<select id="format" name="format" required="required">
						  <option value=".png">.png</option>
						  <option value=".jpeg">.jpeg</option>
						</select>	
					<br><br>
					<button class = "s-btn2" type="submit">Crea prodotto</button>
				</div>
	</form>
	</div>
</div>
</body>
</html>