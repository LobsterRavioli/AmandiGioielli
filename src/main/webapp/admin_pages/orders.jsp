<%@page import="beans.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
 <%
 	Collection<?> orders = (Collection<?>) request.getAttribute("orders");
 	Collection<?> users = (Collection<?>) request.getAttribute("users");
 
 	String error = (String)request.getAttribute("error");

 	
 %>   
   

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Ordini effettuati</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
   	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
</head>
<body>

<%@include file = "../fragments/admin_menu.jsp"%>
<%@include file = "../fragments/admin_header.jsp"%>
	
	
<div class = "main-content">
		<div class = "form-content">
			<form action="<%= request.getContextPath()%>/AdminOrderControl" method="GET">	
				<p class = "research">Ricerca per data</p>
				<br>
				<label for="start-date">Da</label>
				<input type="date" id="start-date" name="start-date">
				<label for="end-date">a</label>
				<input type="date" id="end-date" name="end-date">
				
				<button class="s-btn" type="submit" name="submit">Cerca</button>
			</form>

			<form action="<%= request.getContextPath()%>/AdminOrderControl" method="GET">	
				<p class = "research">Ricerca per codice utente</p>
				<br>
				<label for="customer-id">ID: </label>
				<input type="text" id="customer-id" name="customer-id" value="">
				<button class="s-btn" type="submit" name="submit">Cerca</button>
			</form>
		</div>
		
		<div class = "table-content">
			
			<h2>Ordini</h2>
			
			<table class="product-table">
				<tr>
					<th>Id utente</th>
					<th>Id ordine</th>
					<th>Prezzo</th>
					<th>Data</th>
					<th></th>	
				</tr>
				<%
				if(orders != null && orders.size() > 0) 
				{
				    
				    Iterator<?> it = orders.iterator();
	
						while(it.hasNext())
						{
						    OrderBean bean = (OrderBean) it.next();
							
				%>
							<tr>
								<td><%=bean.getUserId()%></td>
								<td> <%=bean.getId()%></td>
								<td><%=bean.getTotalPrice()%> &euro;</td>
								<td><%=bean.getData()%></td>
			
								<td>
									<a href="<%=request.getContextPath()%>/AdminOrderControl?order_id=<%=bean.getId()%>" class="catalogue-icons">
										<img src="<%=request.getContextPath()%>/images/product-info.png" alt="Get additional informations">
									</a>
								</td>
								<td>
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
		</div>
</div>

</body>
</html>