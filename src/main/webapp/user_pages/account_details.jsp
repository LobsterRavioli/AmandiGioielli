<%@page import="beans.AddressBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Gestione account</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/ajax.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation.js"></script>
</head>
<body>

	<%
		UserBean user = (UserBean) session.getAttribute("user");
		String error = (String)request.getAttribute("error");
	%>
	<%@ include file="../fragments/header.jsp"%>
	<%@ include file="../fragments/user_sidebar.jsp"%>
		<h3>Informazioni Account</h3>
	
		<form action="<%=request.getContextPath()%>/DashBoard" method="POST" id="accountModifyForm">
	
			<input type="hidden" name="scope" value="account">
			
			
			<label for="firstName">Nome:</label><br>
			<input type="text" id="firstName" name="firstName" value="<%=user.getFirstName()%>" required><br>
		
			<label for="lastName">Cognome:</label><br>
			<input type="text" id="lastName" name="lastName" value="<%=user.getLastName()%>" required><br>	
	
			<label for="email">Email:</label><br>
			<input type="email" id="email" name="email" value="<%=user.getEmail()%>" required><br>
			
			<label for="password">Password:</label><br>
			<input type="password" id="password" name="password" value="<%=user.getPassword()%>" required><br>
			
			<label for="phone">Telefono:</label><br>
			<input type="text" id="phone" name="phone" value="<%=user.getPhone()%>" required><br>
			
			<button type="submit">Apporta modifiche</button>
			
		</form>
		
		<div>
			<p id="accountModifyLabel"></p>
		</div>
		
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>