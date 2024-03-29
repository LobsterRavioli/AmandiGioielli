<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="beans.CategoryBean"%>
<%@page import="java.util.Collection"%>
<%@page import="beans.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<meta charset="UTF-8">
	<title>Dettagli prodotto</title>
</head>
<body>


		<%
			String id = (String)request.getParameter("id");
			ProductBean product = (ProductBean) request.getAttribute("productBean");
			
			//Tutte le categorie sul db
			Collection<?> categories = (Collection<?>) request.getAttribute("categories");
		 	String error = (String)request.getAttribute("error");
		 	
		 	if((product == null || categories == null) && error == null) 
		 	{
		 		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/ProductModifyControl?id=" + id));
		 		return;
		 	}
		
		%>
		
		
		<form action="<%=request.getContextPath()%>/ProductModifyControl" enctype="multipart/form-data" method="POST">
			
				<input type="hidden" name="id" value="<%=id%>">
				
				<label for="name">Nome:</label><br>
				<textarea id="name" name="name"  maxlength="30"  required><%=product.getName() %></textarea><br>
				
				<label for="description">Descrizione Prodotto:</label><br>
				<textarea id="description" name="description" maxlength="100" rows="3"  required><%=product.getDescription() %></textarea><br>
				
				<label for="description">Descrizione breve:</label><br>
				<textarea id="shortDescription" name="shortDescription" maxlength="100" rows="3"  required><%=product.getShortDescription() %></textarea><br>
				

				<label for="price">Prezzo:</label><br>
				<input id="price" name="price" type="number" min="0" value=<%=product.getPrice()%> required><br>
				
				<label for="quantity">Quantità:</label><br>
				<input id="quantity" name="quantity" type="number" min="0" value=<%=product.getQuantity()%> required><br>		
				
				<label for="discount">Sconto:</label><br>
				<input id="discount" name="discount" type="number" min="0" value=<%=product.getDiscount()%> required><br>
				
				
				<label for="tax">Tasse applicate (%):</label><br>
				<input id="taxRate" name="taxRate" type="number" min="0" value=<%=product.getTaxRate()%> required><br>
				
				<div id="displayResult">
					
				<%
					//le categorie del singolo prodotto
					ArrayList<CategoryBean> productCategories = product.getCategories();
					boolean flag;
				
					if(categories != null && categories.size() > 0){

						Iterator<?> it1 = categories.iterator();
						while(it1.hasNext()){
						    flag = false;
						    CategoryBean categoryBean = (CategoryBean) it1.next();
						    for(CategoryBean category: productCategories)
								if(category.equals(categoryBean))
								    flag = true;
						    
						    if(flag){		    
								%>
									<input type="checkbox" name ="categories"  value=<%= categoryBean.getId() + "." + categoryBean.getName()%> checked> <%=categoryBean.getName()%> <br>
								<%
						    }
						    else{
								%>
									<input type="checkbox" name ="categories" value=<%= categoryBean.getId()+ "." + categoryBean.getName()%>> <%=categoryBean.getName()%> <br>
								<%
						    }
						}
					}
				%>
				</div>
				<input type="file" name="file" id="file"><br>	
					<select id="format" name="format" size="2" required="required">
					  <option value=".png">.png</option>
					  <option value=".jpeg">.jpeg</option>
					</select>	
				<button type="submit">Apporta modifiche</button>
				
		</form>


</body>
</html>