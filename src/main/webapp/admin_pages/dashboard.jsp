<%@page import="java.sql.DatabaseMetaData"%>
<%@page import="java.sql.ConnectionBuilder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*, javax.sql.DataSource, model.*, utils.Utility"%>
 <%
 	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
 	UserDAO u_mdl = new UserDAO(ds);
 	ProductDAO p_mdl = new ProductDAO(ds);
 	OrderDAO o_mdl = new OrderDAO(ds);
 	Collection<?> products = (Collection<?>)  p_mdl.doRetrieveAll("product_id");
 	Collection<?> user = (Collection<?>)  u_mdl.doRetrieveAll("user_id");
 	Collection<?> orders = (Collection<?>) o_mdl.doRetrieveAll("order_id");	
 	Utility.print("n.utenti: " + user.size());
 	Utility.print("n.ordini: " + orders.size());
 	Utility.print("n.prodotti: " + products.size());
 %> 
 
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
  	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
   	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
</head>
<body>

	<%@include file = "../fragments/admin_menu.jsp"%>
	<%@include file = "../fragments/admin_header.jsp"%>

<div class = "main-content">
	<div class = "content">
		<div class ="cards">
			<div class ="card-single">
				<div>
					<h2><%=user.size()%></h2>
					<span>Customers</span>
				</div>
				<div>
					<span class = "las la-users"></span>
				</div>
			</div>
			
			<div class ="card-single">
				<div>
					<h2><%=orders.size()%></h2>
					<span>Orders</span>
				</div>
				<div>
					<span class = "las la-shopping-bag"></span>
				</div>
			</div>
			
			<div class ="card-single">
				<div>
					<h2><%=products.size()%></h2>
					<span>Catalogue</span>
				</div>
				<div>
					<span class = "las la-folder"></span>
				</div>
			</div>			
		</div>

		<div class = "recent-grid">
			<div class = "customers">
				<div class = "card">
					<div class = "card-header">
						<h2>New customers</h2>
						<button onclick = "">See all <span class = "las la-arrow-right"></span></button>
					</div>
					
					<div class = "card-body">
							<div class = "customer">
								<div class = "info">
									<h4>Customer Info</h4>
								</div>
								
								<div class = "contact">
									<span class = "las la-user-circle"></span>
									<span class = "las la-comment"></span>
									<span class = "las la-phone"></span>
								</div>
							</div>
					</div>
				</div>
			</div>
			
			<div class = "orders">
			</div>			
		</div>
	</div>
</div>
</body>
</html>