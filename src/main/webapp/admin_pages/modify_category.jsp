
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
<head>
<meta charset="UTF-8">
   <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
   	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
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