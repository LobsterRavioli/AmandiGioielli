<%@page import="beans.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli prodotto</title>
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
	<%@include file="../fragments/user_sidebar.jsp" %>
	
	<%	
		Collection<?> ordersDetail = (Collection<?>) request.getAttribute("ordersDetails");
	%>
	<div class="welcome-dashboard">
		<h2>Dettagli ordine</h2>
		<table>
			<tr>
				<th>Nome</th>
				<th>Prezzo</th>
				<th>Quantità</th>
				<th>Subtotale</th>
			</tr>
			<%
			if(ordersDetail!= null && ordersDetail.size() > 0) 
			{
				Iterator<?> it = ordersDetail.iterator();
				while(it.hasNext())
				{
					OrderDetailBean bean = (OrderDetailBean) it.next();
					
			%>
					<tr>
						<td><%= bean.getName() %></td>
						<td><%= bean.getRealPriceString()%> &euro;</td>
						<td><%= bean.getQuantity()%></td>
						<td><%= bean.getRealPriceStringAll()%> &euro;</td>
					</tr>

		<%
				}
			}
		%>
		</table>
	</div>
	
	<%@include file="../fragments/footer.jsp" %>
</body>
</html>