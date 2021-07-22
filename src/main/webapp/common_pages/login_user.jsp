<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation.js"></script>
</head>

<body>


	<%@include file="../fragments/header.jsp" %>


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

		<h2 class="heading-center login-page">Login</h2>
		<span class="line-separator"></span>
		<div class="login">
			<form action="<%=request.getContextPath()%>/LoginControl" method="post" id="loginForm">
	
	    		<input type="email" name="email" id="email" placeholder="Email" required="required"><br>
	   			<input type="password"  name="password" id="password" placeholder="Password" required="required"><br>
	    		<button type="submit">Accedi</button>
	    		
			</form>
		</div>
		<br>
		<div>
			<p id="loginLabel" style="color:red"></p>
		</div>
		<a class="registration-redirect" href="<%=request.getContextPath()%>/common_pages/registration.jsp">Non hai un account? Crealo subito</a>
		
	
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

	<%@include file="../fragments/footer.jsp" %>

</body>

</html>