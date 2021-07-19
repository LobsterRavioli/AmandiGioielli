<%@page import="utils.Utility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,beans.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css">
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
</head>
<body>
	<%@include file="../fragments/header.jsp" %>
	<%@include file="../fragments/menu.jsp" %>
	<% 

	Collection<?> addresses = (Collection<?>) request.getAttribute("addresses");
	 
 	String error = (String)request.getAttribute("error");
 	if(addresses == null) 
 	{
 		response.sendRedirect(request.getContextPath() + "/SummaryOrderControl?action=");
 		return;
 	}
 	
	Cart cart = (Cart)session.getAttribute("cart");
	List<ProductBean> prodcart = cart.getItems();
	
	%>
	<h1>Dettagli ordine</h1>
	
	<table>
		<tr>
			<th>Nome</th>
			<th>Quantità</th>
		</tr>	
	<%
		int quantity = 0;
		if(prodcart.size() > 0) {
		for(ProductBean prod: prodcart) {
			
		%>
		<tr>
			<td><%=prod.getName()%></td>
			<td><%=prod.getQuantity()%></td>
		</tr>
				
		<% 		}
			}
		%>
	</table>
	<h2> Indirizzi</h2>

	<form action="<%=request.getContextPath()%>/SummaryOrderControl">

		<%

			Iterator<?> it = addresses.iterator();
			while(it.hasNext()) {
			AddressBean address = (AddressBean) it.next();
		
			
				
	%>	
		<div id ="displayResult">
				<p> <%=address.getStreetAddress() + " " + address.getAddressNumber() + " " + address.getCity() + " " + address.getProvince() + " " + address.getPhone() %> </p> 
				<input type="radio" name="radios" value="<%=address.getStreetAddress() + " " + address.getAddressNumber() + " " + address.getCity() + " " + address.getProvince() + " " + address.getPhone() %>"
				 checked>
		</div>

			
	<% 		

			}
	%>
		<input type="hidden" name="action" value="buy">
		<input type="submit">
	</form>
	

	
	<h3>Oppure registrane uno nuovo! </h3>
	<div id="result"></div>
	
	<form id="adressForm">
		<input type="text"
	         class="form-control" 
	         id="street"
	         name="street"
	         placeholder="Street">
	  
	  <input type="text"
	         class="form-control" 
	         id="city"
	         name="city"
	         placeholder="Città">
	         
	  <input type="text"
	         class="form-control" 
	         id="addressNumber"
	         name="addressNumber"
	         placeholder="Civico">
	  
	  <input type="text" 
	         class="form-control" 
	         id="province"
	         name="province"
	         placeholder="Provincia">
	  
	  <input type="text" 
	         class="form-control" 
	         id="zip"
	         name="zip"
	         placeholder="Zip">
	         
	   <input type="text" 
	         class="form-control" 
	         id="phone"
			 name="phone"
	         placeholder="Telefono">
	       
	  <input type="submit" value="Registra indirizzo">
	
	</form>

	  


<script>

	$(document).ready(function() {
		
		$("#adressForm").submit(function(){
					$.ajax({
						async: true,
						url: "update",
						type: "POST",
						datatType: "json",
						data: $("#adressForm").serialize(),
						success: function(data){
							var radioButton = document.createElement('input');
							var paragraph = document.createElement('p');
							radioButton.name = "radios";
							radioButton.type = "radio";
							radioButton.value= data.street + ' ' + data.addressNumber + ' ' + data.city + ' ' + data.province + ' ' + data.zip + ' ' + data.phone;
							paragraph.append(data.street + ' ' + data.addressNumber + ' ' + data.city + ' ' + data.province + ' ' + data.zip + ' ' + data.phone);
			
						
							$("#displayResult").append(paragraph);
							$("#displayResult").append(radioButton);
						}
					});
				
	});

	

	function sendData() {
		
	}
});

</script>

	<%
		String message = (String)request.getAttribute("message");
 		error = (String)request.getAttribute("error");
		if(message != null && !message.equals("")) 
		{
	%>
			<p style="color: white; margin-left: auto; margin-right: auto; background-color: green; padding: 5px;"><%=message %></p>
	<%
		}
		if(error != null && !error.equals("")) 
		{
	%>
			<p style="color: white; margin-left: auto; margin-right: auto; background-color: red; padding: 5px;">Errore: <%= error%></p>
	<%
		}
	%>	
	
	
	<div class="spacer-footer"><br> </div>
	
	<%@include file="../fragments/footer.jsp" %>
</body>
</html>