
	<%@page import="beans.CategoryBean"%>
<%@page import="java.util.Collection"%>
<%@page import="beans.ProductBean"%>
<script type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
<%
	String id = (String)request.getParameter("id");
	CategoryBean category = (CategoryBean) request.getAttribute("category");
 	System.out.print("lol");
 	if(category == null) 
 	{

 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/CategoryModifyControl?id=" + id));
 		return;
 	}

%>
<meta charset="UTF-8">
<title>Modifica Categoria</title>
</head>
<body>
		<form action="<%=response.encodeURL("CategoryModifyControl?id=" + category.getId())%>" method="POST">
			
				<input type="hidden" name="action" value="insert">
				
				<label for="description">Descrizione Categoria:</label><br>
				<textarea id="description" name="description" maxlength="100" rows="3"  required><%=category.getDescription() %></textarea><br>
				<button type="submit">Modifica</button>
		</form>
				

</body>
</html>