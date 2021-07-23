<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ControlPanel</title>
  	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
</head>
<body>
	
	<div class = "sidebar">
		<div class = "sidebar-menu">
			<ul>
				<li>
					<a href = "<%=request.getContextPath()%>/admin_pages/dashboard.jsp">
						<span class = "las la-bars">
							<span class = "lbl">Dashboard</span>
						</span>
					</a>
				</li>
				
				<li>
					<a href = "<%=request.getContextPath()%>/admin_pages/orders.jsp">
						<span class = "las la-shopping-bag">
							<span class = "lbl">Orders</span>
						</span>
					</a>
				</li>
				
				<li>
					<a href = "<%=request.getContextPath()%>/admin_pages/catalogue.jsp">
						<span class = "las la-folder">
							<span class = "lbl">Catalogue</span>
						</span>
					</a>
				</li>
				
				<li>
					<a href = "<%=request.getContextPath()%>/admin_pages/customers.jsp">
						<span class = "las la-users">
							<span class = "lbl">Customers</span>
						</span>
					</a>	
				</li>
				
				<li>
					<a href = "<%=request.getContextPath()%>/admin_pages/categories.jsp">
						<span class = "las la-users">
							<span class = "lbl">Categorie</span>
						</span>
					</a>	
				
				</li>
				
			</ul>
		</div>
	</div> 
</body>