
<%
 Locale locale = request.getLocale();
 String language = locale.getLanguage();
 String country = locale.getCountry();
%>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Protected Page</title>
   	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	</head>
<body>
	<h1>Welcome to the Protected Page</h1>
	Congratulations. You have accessed a protected document.
	<br><br>
	<%= language %><br>
	<%= country%><br>

	<form action="<%=request.getContextPath()%>/LogOutControl" method="post" > 
	     <input type="submit" value="Logout"/>
</form> 
</body>
</html>