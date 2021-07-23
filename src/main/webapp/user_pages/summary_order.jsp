<%@page import="utils.Utility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,beans.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	
</head>
<body>
	<%@include file="../fragments/header.jsp" %>

	<% 

	Collection<?> addresses = (Collection<?>) request.getAttribute("addresses");
	 
 	String error = (String)request.getAttribute("error");
 	if(addresses == null && error == null) 
 	{
 		response.sendRedirect(request.getContextPath() + "/SummaryOrderControl");
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
	<form action="" id="paymentForm">
               <h3>Pagamento</h3>
               <label for="cname">Proprietario Carta</label>
               <input type="text" id="cname" name="cardname" placeholder="Mario Rossi"><br>
               <label for="ccnum">Numero carta</label>
               <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444"><br>
               <label for="expmonth">Mese di scadenza</label>
               <input type="text" id="expmonth" name="expmonth" placeholder="Settembre"><br>


                  <label for="expyear">Anno di scadenza</label>
                  <input type="text" id="expyear" name="expyear" placeholder="2018"><br>

                  <label for="cvv">CVV</label>
                  <input type="text" id="cvv" name="cvv" placeholder="352"><br>
              <br>
    </form>
	<%
	if(addresses != null)
		if(addresses.size() > 0){
		    
	
	%>
	<h2> Indirizzi</h2>

	<form action="<%=request.getContextPath()%>/SummaryOrderControl">

		<%

			Iterator<?> it = addresses.iterator();
			while(it.hasNext()) {
			AddressBean address = (AddressBean) it.next();
		
			
				
	%>	
		<div id ="displayResult">
				
				<input type="radio" name="radios" value="<%=address.getStreetAddress() + " - " + address.getCity() + " - " + address.getProvince() + " - "+ address.getZip()  + " - " + address.getPhone()%>"
				 checked>
				 <p class="address-radio"> <%=address.getStreetAddress() + " - " + address.getCity() + " - " + address.getProvince() + " - "+ address.getZip()  + " - " + address.getPhone() %> </p> 
		</div>

			
	<% 		

			}
	%>
		<input type="hidden" name="action" value="noForm">
		<button type="submit" id="finish-order">Acquista</button>
		</form>
	<%	
		}
		else{
	%>
		<h3 id="address-heading">Registra un indirizzo! </h3>
		<form action="<%=request.getContextPath()%>/SummaryOrderControl" id="addressOrderForm">

		<div id="result"></div>
		
			  <input type="text"
			         class="form-control" 
			         id="street"
			         name="street"
			         placeholder="Via">
			  
			  <input type="text"
			         class="form-control" 
			         id="city"
			         name="city"
			         placeholder="Città">
			         
			  
			  <input type="text" 
			         class="form-control" 
			         id="province"
			         name="province"
			         maxlength="2"
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
		
			  <input type="hidden" name="action" value="form">   
			  <button type="submit">Registra</button>

		</form>
		


	<%
		}
	%>
	  

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
	
	

	
	<%@include file="../fragments/footer.jsp" %>
</body>
</html>