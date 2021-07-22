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
    <title>Amandi Gioielli - Gestione indirizzi</title>
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
	<%@ include file="../fragments/header.jsp"%>
	<%@ include file="../fragments/user_sidebar.jsp"%>
	
	<div class="welcome-dashboard">
		<h3>Indirizzi:</h3>
			<div id ="displayResult">
		<%
	
				Collection<?> addresses = (Collection<?>) request.getAttribute("addresses");
	 			String error = (String)request.getAttribute("error");
				if(addresses == null) 
				{
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/DashBoard?scope=addresses"));
					return;
				}
				
				Iterator<?> it = addresses.iterator();
				while(it.hasNext()) {
					AddressBean address = (AddressBean) it.next();	
		%>	
					<p> <%=address.getStreetAddress() + " - "  + address.getCity() + " - " + address.getProvince() + " - " + address.getZip() + " - " + address.getPhone() %> </p> 
				
		<%		 		
				}
		%>
		</div>
			<h3>Metti un nuovo indirizzo:</h3>
			<form action="" id="addressForm">
			
				<div id="result">
				</div>
				
					<input type="text"
				         class="form-control" 
				         id="street"
				         name="street"
				         placeholder="Via">
				  
				  <input type="text"
				         class="form-control" 
				         id="city"
				         name="city"
				         placeholder="Città">
				         
				  
				  <input type="text" 
				         class="form-control" 
				         id="province"
				         name="province"
				         placeholder="Provincia"
				         maxlength="2">
				  
				  <input type="text" 
				         class="form-control" 
				         id="zip"
				         name="zip"
				         placeholder="Zip">
				         
				   <input type="text" 
				         class="form-control" 
				         id="phone"
						 name="phone"
				         placeholder="Telefono">
		  
				  <button type="submit">Inserisci</button>
	
			</form>
			
			<div>
				<%
					if(error != null)
					{
				%>
						<p><%=error%></p>
				<% 	
					}
				%>
			
	
			</div>
		</div>
		
		
		
		<%@ include file="../fragments/footer.jsp"%>
</body>
</html>