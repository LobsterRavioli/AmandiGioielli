<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
 <%
 	Collection<?> products = (Collection<?>) request.getAttribute("products");
 
 	String error = (String)request.getAttribute("error");
 	
 	String title = (String)request.getAttribute("title");
 	if(products == null && error == null) 
 	{
 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/ProductControl?page=catalogue"));
 		return;
 	}
 %>   
   

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Catalogo</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<%@include file="../fragments/header.jsp" %>
	
	


	<div class="big-container">
	
		
		<%
			if(title == null)
			{
		%>
				<h2 class="heading-center">Catalogo</h2>
		<% 
			}
			else
			{
		%>	
				<h2 class="heading-center"><%=title%></h2>
		<%
			}
		%>	
			
		

		<span class="line-separator"></span>
		<div class="row">
			<%
			if(products != null && products.size() > 0) 
			{
				Iterator<?> it = products.iterator();
				while(it.hasNext())
				{
					ProductBean bean = (ProductBean) it.next();
					
		%>
	
						<div class="col-4">
							<a href="<%=request.getContextPath()%>/ProductControl?action=details&id=<%=bean.getCode()%>"><img src="<%=bean.getUrl()%>"></a>
							<h4><%=bean.getName()%></h4>
							<div class="product-details">
								<p><%=bean.getRealPriceString()%> &euro;</p>
								<a href="<%=request.getContextPath()%>/ProductControl?action=details&id=<%=bean.getCode()%>" class="add-cart-home">Dettagli</a>
							</div>
						</div>
							
						<%
				}
			}
						%>
		</div>

	</div>

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
	<br>
	
	<%@include file="../fragments/footer.jsp" %>

		
</body>
</html>