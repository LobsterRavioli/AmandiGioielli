$("document").ready(function(){
	var image = document.querySelector("#product-img");
	
	$("#product-img").mousemove(function(e){
		
		var width = image.offsetWidth;
        var height = image.offsetHeight;
        var mouseX = e.offsetX;
        var mouseY = e.offsetY;

        var bgPosX = (mouseX / width *100);
        var bgPosY = (mouseY / height *100);

        image.style.backgroundPosition = `${bgPosX}% ${bgPosY}%`;
	});
});