<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/style.css" type="text/css">
    <script type="text/javascript"></script>
    
</head>

	<%@include file="../fragments/header.jsp" %>
	<%@include file="../fragments/menu.jsp" %>
	



<body onLoad="document.registration.userid.focus();">
	   
	<div>
    	<form name="registration" action="<%=request.getContextPath()%>/RegistrationControl" onsubmit="event.preventDefault(); validate(this)" method="post">
		    <label for="name"><b>Nome</b></label>
		    <input type="text" placeholder="Inserisci Nome" name="name" id="name" required="required">
		  
		    <label for="surname"><b>Cognome</b></label>
		    <input type="text" placeholder="Inserisci Cognome" name="surname"  id="surname" required="required">
		  
		    <label for="email"><b>Email</b></label>
		    <input type="text" placeholder="Inserisci Email" name="email" id="email" required="required">
		
   		    <label for="password"><b>Password</b></label>
		    <input type="password" name="password" id="password" placeholder="Inserisci Password"  required="required">
		    
		    
   		    <label for="newsletter">Iscriviti alla newsletter</label>
		    <input type="checkbox" id="newsletter" name="newsletter" value="true">
		    <input type="submit" value="Crea un account">
		    
		</form>
		
	</div>
	
		<script>
	 
		function removePhone(idd) {
			var element = document.getElementById(idd);
			element.parentNode.removeChild(element);
		}


		function checkNamesurname(inputtxt) {
			var name = /^[A-Za-z]+$/;;
			if(inputtxt.value.match(name)) 
				return true

			return false;	
		}

		function checkEmail(inputtxt) {
			var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			if(inputtxt.value.match(email)) 
				return true;
			
			return false;	
		}

		function checkPhonenumber(inputtxt) {
			var phoneno = /^([0-9]{3}-[0-9]{7})$/;
			if(inputtxt.value.match(phoneno)) 
				return true;
			
			return false;
		}


		function validate(obj) {	
			var valid = true;	
			
			var name = document.getElementsByName("name")[0];
			if(!checkNamesurname(name)) {
				valid = false;
				name.classList.add("error");
			} else {
				name.classList.remove("error");
			}
			
			var surname = document.getElementsByName("surname")[0];
			if(!checkNamesurname(surname)) {
				valid = false;
				surname.classList.add("error");
			} else {
				surname.classList.remove("error");
			}
			
			var email = document.getElementsByName("email")[0];
			if(!checkEmail(email)) {
				valid = false;
				email.classList.add("error");
			} else {
				email.classList.remove("error");
			}	
				
			
			if(valid) obj.submit();
		}
		</script>
	<div class="spacer-footer"><br> </div>
	<%@include file="../fragments/footer.jsp" %>

</body>
</html>