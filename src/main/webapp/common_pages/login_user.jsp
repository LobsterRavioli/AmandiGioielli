<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css">
</head>

<body>


	<%@include file="../fragments/header.jsp" %>
	<%@include file="../fragments/menu.jsp" %>


	<%

	String error = (String)request.getAttribute("error");
	
	
	if(session.getAttribute("user") != null)
	{
		response.sendRedirect(request.getContextPath()+"/common_pages/home.jsp");
		
	%>
		
	<% 
	}
	else
	{
	%>

		<div>
			<form action="<%=request.getContextPath()%>/LoginControl" method="post">
	
	    		<label for="email"><b>Email</b></label>
	    		<input type="email" name="email" id="email" placeholder="Inserisci Email" required="required">
				<label for="password"><b>Password</b></label>
	   			<input type="password"  name="password" id="password" placeholder="Inserisci Password" required="required">
	    		<input type="submit" value="Accedi">
	    		
			</form>
		</div>
		<br>
		<%
		String message = (String)request.getAttribute("message");
		if(message != null && !message.equals("")) 
		{
		%>
			<p style="color: white; margin-left: auto; margin-right: auto; background-color: red; padding: 5px;"><%=message %></p>
		<%
		}
		if(error != null && !error.equals("")) 
		{
		%>
			<p style="color: white; margin-left: auto; margin-right: auto; background-color: red; padding: 5px;">Errore: <%= error%></p>
		<%
		}
	%>
		

<%
	}
%>

	<div class="spacer-footer"><br> </div>
	<%@include file="../fragments/footer.jsp" %>

</body>

</html>