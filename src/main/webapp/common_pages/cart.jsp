<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Carrello</title>
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
	 	Cart cart = (Cart)session.getAttribute("cart");
		
	 	if(cart == null) {
	 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/CartControl"));
	 		return;
	 	}	
		
		List<ProductBean> prodcart = cart.getItems();
	%>
	<h2 class="heading-center">Carrello</h2>
	<span class="line-separator"></span>


	<table class="cart-table-desktop">
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th>Prodotto</th>
					<th>Prezzo</th>
					<th>Quantit&agrave; </th>
					<th>Subtotale</th>
				</tr>	
			</thead>
			<tbody>
			<%
			int quantity = 0;
				if(prodcart.size() > 0) {
					for(ProductBean prod: prodcart) {
						
						
			%>
			
					<tr>
						<td><a href="<%=response.encodeURL(request.getContextPath() + "/CartControl?action=deleteCart&id=" + prod.getCode())%>"><i class="fas fa-times" style="color:red;"></i></a></td>
						<td>
							<div class="cart-info">
								<img src="<%=prod.getUrl()%>">
							</div>
						</td>
						<td><%=prod.getName()%></td>
						<td><%=prod.getRealPriceString()%> &euro;</td>
						<td><%=prod.getQuantity()%></td>
						<td><%=prod.getRealPriceStringAll()%> &euro;</td>
					</tr>
				

				
		<% 		}
			} else {
		%>
			<tr><td colspan="6">Nessun prodotto nel carrello</td></tr>
		<%
			}
		%>
		</tbody>
	</table>
	
	
	<table class="cart-table-mobile">
			<thead>
				<tr>
					<th></th>
					<th></th>
					<th>Quantit&agrave; </th>
					<th>Subtotale</th>
				</tr>	
			</thead>
		<tbody>
		<%
			if(prodcart.size() > 0) {
				for(ProductBean prod: prodcart) {
					
					
		%>
			
					<tr>
						<td><a href="<%=response.encodeURL(request.getContextPath() + "/CartControl?action=deleteCart&id=" + prod.getCode())%>"><i class="fas fa-times" style="color:red;"></i></a></td>
						<td>
							<div class="cart-info">
								<img src="<%=prod.getUrl()%>">
							</div>
						</td>
						<td><%=prod.getQuantity()%></td>
						<td><%=prod.getRealPriceStringAll()%> &euro;</td>
					</tr>
				
		<% 		}
			} else {
		%>
			<tr><td colspan="6">Nessun prodotto nel carrello</td></tr>
		<%
			}
		%>
		</tbody>
	</table>
	
	<div class="cart-total">
		<h3>Totale (tasse applicate):</h3>
		<p><%=Double.toString(cart.getTotalValue()).replace(".", ",")%> &euro;</p>
	</div>
	
	<% 
		if(prodcart.size() > 0) 
		{
			
	%>
			<div class="cart-button">
				<a href="<%=response.encodeURL(request.getContextPath()+"/CartControl?action=clearCart")%>" >Svuota </a>
				<%
					if(session.getAttribute("user") != null)
					{
				%>
						<a href="<%=response.encodeURL(request.getContextPath()+"/CartControl?action=buy")%>">Acquista</a>
				<%
					}
					else
					{
				%>
						<a href="<%=response.encodeURL(request.getContextPath()+"/common_pages/login_user.jsp")%>">Acquista</a>
				<%
					}
				%>
			</div>
	<%  } %>
	
	
	<%
		String message = (String)request.getAttribute("message");
 		String error = (String)request.getAttribute("error");
		if(message != null && !message.equals("")) 
		{
	%>
			<div class="alert-cart">
				<p><%=message %><i class="fas fa-check-circle"></i></p>
			</div>
			
			
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