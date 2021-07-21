	/*AGGIORNAMENTO INDIRIZZI DASHBOARD UTENTE*/
	$(document).ready(function() {
		$("#addressForm").submit(function(){
			
			event.preventDefault();
			
			var form = $(this).closest("#addressForm");  

			$.ajax({
				async: true,
				url: "./update",
				type: "POST",
				datatType: "json",
				data: form.serialize(),
				success: function(data){
					var paragraph = document.createElement('p');
					paragraph.append(data.zip + ' ' + data.streetAddress + ' ' + data.city + ' ' + data.province +  ' ' + data.phone);
					$("#displayResult").append(paragraph);
				}
			});
				
		});
	
	});