<%@page import="java.sql.DatabaseMetaData"%>
<%@page import="java.sql.ConnectionBuilder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*, javax.sql.DataSource, model.*, utils.Utility"%>
     
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
  	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_style.css" type="text/css">
</head>
<body>
<div class = "main-content">
		<header>
			<h2>
				<label for = "nav-toggle">
					<span class = "las la-bars"></span>
				</label>
				
				Admin Header	
			</h2>
			
			<div class = "user-wrapper">
				<img src ="" width = "40px" height = "40px" alt = "">
				<div>
					<h4>Admin_name</h4>
				</div>
			</div>
		</header>
</div>
</body>