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
</head>
<body>
	<%@include file="../fragments/header.jsp" %>
	<%@include file="../fragments/menu.jsp" %>
	<% 
	 	Cart cart = (Cart)session.getAttribute("cart");
		
	 	if(cart == null) {
	 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/CartControl"));
	 		return;
	 	}	
	%>
	<h2>Carrello</h2>
	<%
		List<ProductBean> prodcart = cart.getItems();
	%>

	<table>
		<tr>
			<th>Nome</th>
			<th>Quantità</th>
			<th></th>
		</tr>	
		<%
		int quantity = 0;
			if(prodcart.size() > 0) {
				for(ProductBean prod: prodcart) {
					
					
		%>
				<tr>
					<td><%=prod.getName()%></td>
					<td><%=prod.getQuantity()%></td>
					<td>
						<a href="<%=response.encodeURL(request.getContextPath() + "/CartControl?action=deleteCart&id=" + prod.getCode())%>" class="catalogue-icons">
							<img src="<%=request.getContextPath()%>/images/remove-from-catalogue.png">
						</a>
					</td>
				</tr>
				
		<% 		}
			} else {
		%>
			<tr><td colspan="3">Nessun prodotto nel carrello</td></tr>
		<%
			}
		%>
	</table>
	
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
						<a href="<%=response.encodeURL(request.getContextPath()+"/common_pages/login.jsp")%>">Acquista</a>
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