<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Amandi Gioielli - Login Admin</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/admin_login.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/validation.js"></script>
</head>
<body>

		<h2 class="heading-center login-page">Admin Login</h2>
        <span class="line-separator"></span>
        <div class="login">
            <form action="<%=request.getContextPath()%>/LoginAdmin" method="post" id="loginAdminForm">

                <input type="text" name="username" id="username" placeholder="Username" required="required"><br>
                   <input type="password"  name="password" id="password" placeholder="Password" required="required"><br>
                <button type="submit">Accedi</button>

            </form>
        </div>
        <br>
        <div>
            <p id="loginAdminLabel" style="color:red"></p>
        </div>
        
</body>
</html>
