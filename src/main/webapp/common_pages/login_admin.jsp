<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/LoginAdmin" method="post"> 
<fieldset>
     <legend>Accesso al pannello admin</legend>
     <label for="username">Login</label>
     <input id="username" type="text" name="username" placeholder="enter login"> 
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password"> 
     <br>
     <input type="submit" value="Login"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form> 

</body>
</html>
