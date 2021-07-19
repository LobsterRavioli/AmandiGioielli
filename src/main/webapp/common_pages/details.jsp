<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,beans.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli prodotto</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css">
</head>
<body>
	<%@include file="../fragments/header.jsp" %>
	<%@include file="../fragments/menu.jsp" %>
	<%	
		ProductBean product = (ProductBean) request.getAttribute("product");
	%>

	<h2>Dettagli</h2>
	<table>
		<tr>
			<th>Codice</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Prezzo</th>
			<th>Iva</th>
			<th>Quantità</th>
			<th> </th>
		</tr>
		<tr>
			<td><%=product.getCode()%></td>
			<td><%=product.getName()%></td>
			<td><%=product.getDescription()%></td>
			<td><%=product.getPrice()%> &#8364;</td>
			<td><%=(product.getTaxRate() /100) * product.getPrice()%> &#8364;</td>
			<td><%=product.getQuantity()%></td>
			<td>							
				<a href="<%=response.encodeURL("ProductControl?action=addCart&id=" + product.getCode())%>" class="catalogue-icons">
					<img src="./images/add-to-cart.png" alt="Add product to shopping cart">
				</a>	
			</td>
		</tr>
	</table>
	
	<div class="spacer-footer"><br> </div>
	
	<%@include file="../fragments/footer.jsp" %>
</body>
</html>