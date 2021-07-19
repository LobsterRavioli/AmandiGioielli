<%@page import="beans.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
 <%
 	Collection<?> orders = (Collection<?>) request.getAttribute("orders");
 	Collection<?> users = (Collection<?>) request.getAttribute("users");
 
 	String error = (String)request.getAttribute("error");
 	
 	if(orders == null && error == null) 
 	{
 		response.sendRedirect(response.encodeRedirectURL("../AdminOrderControl"));
 		return;
 	}
 	
 %>   
   

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Ordini effettuati</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
</head>
<body>
	<%@include file="../fragments/admin_menu.jsp" %>
	<%@include file="../fragments/admin_header.jsp" %>
	
<div class = "main-content">
	<div class = "table-content">
		<h2>Ordini</h2>
		<table class="product-table">
			<tr>
				<th style="width: 30%">Id utente</th>
				<th style="width: 20%">Id ordine</th>
				<th style="width: 60%">Prezzo</th>
				<th style="width: 5%">Dettagli</th>
				<th style="width: 15%"></th>	
			</tr>
			<%
			if(orders != null && orders.size() > 0) 
			{
				if(users != null && users.size() > 0) 
				{
					Iterator<?> it = orders.iterator();
					Iterator<?> it2 = users.iterator();
					while(it.hasNext() && it2.hasNext())
					{
						OrderBean bean = (OrderBean) it.next();
						UserBean u_bean = (UserBean) it2.next();
						
			%>
						<tr>
							<td><%=u_bean.getId()%></td>
							<td><%=bean.getId()%></td>
							<td><%=bean.getTotalPrice()%></td>
		
							<td>
								<a href="<%= request.getContextPath() + "/AdminOrderControl?order_id=" + bean.getId() %>" class="catalogue-icons">
									<img src="<%=request.getContextPath()%>/images/product-info.png" alt="Get additional informations">
								</a>
							</td>
							<td>
						</tr>
			<%
					}
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