<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Creazione prodotto</title>
</head>
<body>
	<form action=<%= request.getContextPath() + "/RegistrationProductControl"%> enctype="multipart/form-data" method="POST">
			
				<input type="hidden" name="action" value="insert">
				
				<label for="name">Nome:</label><br>
				<input id="name" name="name" type="text" ><br>
				
				<label for="description">Descrizione Prodotto:</label><br>
				<textarea id="description" name="description" maxlength="100" rows="3"  ></textarea><br>
				
				<label for="description">Descrizione breve:</label><br>
				<textarea id="shortDescription" name="shortDescription" maxlength="100" rows="3"></textarea><br>
				

				<label for="price">Prezzo:</label><br>
				<input id="price" name="price" type="number" min="0" ><br>
				
				<label for="quantity">Quantità:</label><br>
				<input id="quantity" name="quantity" type="number" min="0"><br>		
				
				<label for="discount">Sconto:</label><br>
				<input id="discount" name="discount" type="number" min="1"  required><br>
				
				<label for="taxRate">Tax Rate:</label><br>
				<input id="taxRate" name="taxRate" type="number" min="1"  required><br>
				<label for="file">Immagini da caricare:</label>
				<input type="file" name="file" id="file" multiple><br>	
					<select id="format" name="format" size="2" required="required">
					  <option value=".png">.png</option>
					  <option value=".jpeg">.jpeg</option>
					</select>	
				<button type="submit">Crea prodotto</button>
	</form>
</body>
</html>