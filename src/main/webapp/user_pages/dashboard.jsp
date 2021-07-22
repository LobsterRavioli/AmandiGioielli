<%@page import="control.LogOutControl"%>
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
    <title>Amandi Gioielli - Dashboard account</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/ajax.js"></script>
</head>

<body>

	<%
		UserBean user = (UserBean) session.getAttribute("user");
		String error = (String)request.getAttribute("error");

	
	%>
	<%@ include file="../fragments/header.jsp"%>
	<%@ include file="../fragments/user_sidebar.jsp"%>
	
	<div class="welcome-dashboard">

		<h3>Informazioni Account</h3>
		Ciao <span style="color: #00ddc9"><%=user.getFirstName()%></span> (non sei <%=user.getFirstName()%>? <a style="color: #00ddc9" href="<%=request.getContextPath()%>/LogOutControl">Logout</a>)
		Dalla bacheca del tuo account puoi visualizzare i tuoi ordini recenti, gestire i tuoi indirizzi e modificare la password e i dettagli dell'account.
	</div>


	
	<%@ include file="../fragments/footer.jsp"%>
</body>
</html>