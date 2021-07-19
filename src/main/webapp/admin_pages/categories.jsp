<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
 <%
 	Collection<?> categories = (Collection<?>) request.getAttribute("categories");

 
 	String error = (String)request.getAttribute("error");
 	
 	if(categories == null && error == null) 
 	{
 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/AdminCategoryControl"));
 		return;
 	}

 %>   
   

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categorie</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type = "text/css">
   	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
</head>
<body>

	<%@include file = "../fragments/admin_menu.jsp"%>
	<%@include file = "../fragments/admin_header.jsp"%>
	

<div class = main-content>
	<div class = "cat-content">
		<h2>Categorie</h2>
		<table class="product-table">
			<tr>
				<th style="width: 20%">Nome</th>
				<th style="width: 60%">Descrizione</th>
				<th style="width: 15%"></th>	
			</tr>
			<button><a href= <%=request.getContextPath()+"/admin_pages/create_category.jsp"%>>Crea nuova categoria </a></button>
			<%
			if(categories != null && categories.size() > 0) 
			{
				Iterator<?> it = categories.iterator();
				while(it.hasNext())
				{
				CategoryBean bean = (CategoryBean) it.next();
					
		%>



		
					<tr>
						<td><%=bean.getName()%></td>
						<td><%=bean.getDescription()%></td>
	
						<td>
							<a href="<%=response.encodeURL(request.getContextPath()+"/admin_pages/modify_category.jsp?&id=" + bean.getId())%>" class="catalogue-icons">
								Modifica
							</a>
							<a href="<%=response.encodeURL(request.getContextPath()+"/AdminCategoryControl?action=delete&id=" + bean.getId())%>" class="catalogue-icons">
								<img src="<%=request.getContextPath()%>/images/remove-from-catalogue.png" alt="Remove product from catalogue">
							</a>	
						</td>
					</tr>
		<%
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
		<br>

	</div>
</div>
</body>
</html>