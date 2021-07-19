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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css">
</head>
<body>
	<%@include file="../fragments/header.jsp" %>

	<%	
		Collection<OrderDetailBean> ordersDetail = (Collection<OrderDetailBean>) request.getAttribute("ordersDetails");
		LinkedList<OrderDetailBean> list =(LinkedList) ordersDetail;
	%>

	<h2>Dettagli ordine</h2>
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
		<%
		if(!list.isEmpty()){
			for(OrderDetailBean detail : list){
				
		%>
		<tr>
			<td><%= detail.getName() %></td>
			<td><%= detail.getDiscount() %></td>
			<td><%= detail.getPrice()%></td>
			<td><%= detail.getQuantity()%></td>
			<td>								
			</td>
		</tr>
	</table>
	<%
			}
		}
	%>
	
	<div class="spacer-footer"><br> </div>
	
	<%@include file="../fragments/footer.jsp" %>
</body>
</html>