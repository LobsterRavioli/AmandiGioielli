<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Registrazione</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation.js"></script>
    
</head>

	<%@include file="../fragments/header.jsp" %>

	
	<%
		String msg = (String) request.getAttribute("message");
	%>


<body>
	   
	<h2 class="heading-center">Crea un account</h2>
	<span class="line-separator"></span>
	<div class="login">
    	<form name="registration" action="<%=request.getContextPath()%>/RegistrationControl" method="post" id="registrationForm">
		    <input type="text" placeholder="Nome" name="name" id="name" required="required">
		  
		    <input type="text" placeholder="Cognome" name="surname"  id="surname" required="required">
		  
		    <input type="text" placeholder="Email" name="email" id="email" required="required">
		
		    <input type="password" name="password" id="password" placeholder="Password"  required="required">
		    
		    <button type="submit">Registrati</button>
		    
		</form>
		
	</div>
	
	<div>
		<%
		if(msg!=null)
		{
		%>
			<p id="registrationLabel" style="color: red"><%=msg%><i class="fas fa-exclamation-triangle"></i></p>
		<%
		} 
		%>
		
		<%
		if(msg==null)
		{
		%>
			<p id="registrationLabel" style="color: red"></p>
		<%
		} 
		%>
	</div>
	

	<%@include file="../fragments/footer.jsp" %>

</body>
</html>