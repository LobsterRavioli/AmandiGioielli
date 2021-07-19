	 
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