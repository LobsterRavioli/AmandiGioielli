<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
 <%
 	Collection<?> products = (Collection<?>) request.getAttribute("products");
 
 	String error = (String)request.getAttribute("error");
 	
 	if(products == null && error == null) 
 	{
 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/ProductControl"));
 		return;
 	}
 %>   
   

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Catalogo</title>
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
	<h2>Catologo</h2>
	<table class="product-table">
		<tr>
			<th style="width: 20%">Nome</th>
			<th style="width: 50%">Descrizione</th>
			<th style="width: 5%">Prezzo</th>
			<th style="width: 15%"></th>	
			<th style="width: 15%">Immagine</th>
		</tr>
		<%
		if(products != null && products.size() > 0) 
		{
			Iterator<?> it = products.iterator();
			while(it.hasNext())
			{
			ProductBean bean = (ProductBean) it.next();
				
	%>
				<tr>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>
					<td><%=bean.getPrice() + ((bean.getTaxRate() / 100) * bean.getPrice())%> &#8364;</td>

					<td>
						<a href="<%=response.encodeURL(request.getContextPath()+"/ProductControl?action=details&id=" + bean.getCode())%>" class="catalogue-icons">
							<img src="<%=request.getContextPath()%>/images/product-info.png" alt="Get additional informations">
						</a>
						<a href="<%=response.encodeURL(request.getContextPath()+"/ProductControl?action=delete&id=" + bean.getCode())%>" class="catalogue-icons">
							<img src="<%=request.getContextPath()%>/images/remove-from-catalogue.png" alt="Remove product from catalogue">
						</a>
						<a href="<%=response.encodeURL(request.getContextPath()+"/ProductControl?action=addCart&id=" + bean.getCode())%>" class="catalogue-icons">
							<img src="<%=request.getContextPath()%>/images/add-to-cart.png" alt="Add product to shopping cart">
						</a>	
					</td>
					<td>
						<img src="<%=bean.getUrl()%>">
					</td>
				</tr>
	<%
			}
		}
	%>
	</table>
	<br>
	<%
		String message = (String)request.getAttribute("message");
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
	<br>
	
	<%@include file="../fragments/footer.jsp" %>

		
</body>
</html>