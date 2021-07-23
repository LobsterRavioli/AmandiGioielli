<%@page import="beans.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli prodotto</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/admin_styles/style.css" type="text/css">
</head>
<body>

	<%@include file="../fragments/admin_header.jsp" %>
	<%@include file="../fragments/admin_menu.jsp" %>

	<%	
		Collection<OrderDetailBean> ordersDetail = (Collection<OrderDetailBean>) request.getAttribute("ordersDetails");
		LinkedList<OrderDetailBean> list =(LinkedList) ordersDetail;
	%>

<div class = "form-content details-admin">
	<h2>Dettagli ordine</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Prezzo</th>
			<th>Quantità</th>
			<th> </th>
		</tr>
		<%
		if(!list.isEmpty()){
			for(OrderDetailBean detail : list){
				
		%>
		<tr>
			<td><%= detail.getId() %></td>
			<td><%= detail.getName() %></td>
			<td><%= detail.getRealPriceString()%> &euro;</td>
			<td><%= detail.getQuantity()%></td>
			<td>								
			</td>
		</tr>

	<%
			}
		}
	%>
	</table>
</div>
</body>
</html>