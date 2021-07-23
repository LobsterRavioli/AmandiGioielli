function checkEmail(inputtxt) {
	var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.val().match(email)) 
		return true;
	
	inputtxt.focus();
	return false;	
}

function onlyLetters(name) {
	
	var letters = /^[A-Za-z]+$/;
	
	if(name.val().match(letters)) {
		return true;
	}
	else {
		name.focus();
		return false;
	}
}

function checkAdmin(username) {
/* 
 Usernames can only have: 
    - Lowercase Letters (a-z) 
    - Numbers (0-9)
    - Dots (.)
    - Underscores (_)
*/

	const res = /^[a-z0-9_\.]+$/;
	if(username.val().match(res)) 
		return true;

	username.focus();
	return false;	
}

function checkPhonenumber(inputtxt) {
	var phoneno = /^([0-9]{10})$/;
	if(inputtxt.val().match(phoneno)) 
		return true;
	
	return false;
}

$(document).ready(function(){
	
	// VALIDAZIONE LOGIN UTENTE
	$("#loginForm").submit(function(event){
		if(!checkEmail($("#loginForm input[name=email]"))) {
			$("#loginLabel").html('Inserisci una email valida<i class="fas fa-exclamation-triangle"></i>');
			return false;
		}
	});
	
	// VALIDAZIONE REGISTRAZIONE UTENTE
	$("#registrationForm").submit(function(event){
		
		
		var name = onlyLetters($("#registrationForm input[name=name]"));
		var surname = onlyLetters($("#registrationForm input[name=surname]"))
		var email = checkEmail($("#registrationForm input[name=email]"));
		
		if(!name || !surname || !email ) {
			$("#registrationLabel").html('Dati inseriti non validi<i class="fas fa-exclamation-triangle"></i>');
			return false;
		}
	});	
	
	//VALIDAZIONE ADMIN LOGIN
	
	$("#loginAdminForm").submit(function(event){
			if(!checkAdmin($("#loginAdminForm input[name=username]"))) {
			$("#loginAdminLabel").html('Dati inseriti non validi<i class="fas fa-exclamation-triangle"></i>');
			return false;
		}
	});
	
	
	// VALIDAZIONE MODIFICA USER
	$("#accountModifyForm").submit(function(event){
		
		$("#accountModifyLabel").empty();
		
		var name = onlyLetters($("#accountModifyForm input[name=firstName]"));
		var surname = onlyLetters($("#accountModifyForm input[name=lastName]"))
		var email = checkEmail($("#accountModifyForm input[name=email]"));
		var phone = checkPhonenumber($("#accountModifyForm input[name=phone]"));
		
		if(!name || !surname || !email || !phone ) {
			$("#accountModifyLabel").html('Dati inseriti non validi<i class="fas fa-exclamation-triangle"></i>');
			return false;
		}
	});	
	
});