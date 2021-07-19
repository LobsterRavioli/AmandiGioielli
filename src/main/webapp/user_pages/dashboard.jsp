<%@page import="beans.AddressBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	UserBean user = (UserBean) session.getAttribute("user");
	Collection<?> addresses = (Collection<?>) request.getAttribute("addresses");

	String error = (String)request.getAttribute("error");
	if(addresses == null) 
	{
		response.sendRedirect(request.getContextPath() + "/DashBoard?action=");
		return;
	}

%>
<html>
<head>
<meta charset="UTF-8">
    <title>Amandi Gioielli - DashBoard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css">
</head>
<body>
	<h3>Informazioni Account</h3>
	
	<p>Nome:<%=user.getFirstName()%> </p> <br>
	<p>Cognome:<%=user.getLastName()%></p><br>
	<p>Email:<%=user.getEmail()%></p><br>
	<p>Cellulare:<%=user.getPhone()%>
	<h3>Indirizzi:</h3>
	<%

			Iterator<?> it = addresses.iterator();
			while(it.hasNext()) {
			AddressBean address = (AddressBean) it.next();
		
			
				
	%>	
		<div id ="displayResult">
				<p> <%=address.getStreetAddress() + " " + address.getAddressNumber() + " " + address.getCity() + " " + address.getProvince() + " " + address.getPhone() %> </p> 
					<% 		

			}
	%>

</body>
</html>