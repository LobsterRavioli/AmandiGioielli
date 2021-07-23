<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,beans.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli prodotto</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
		<script src="<%=request.getContextPath()%>/js/zoom.js"></script>
</head>
<body>
	<%@include file="../fragments/header.jsp" %>
	<%	
		ProductBean product = (ProductBean) request.getAttribute("product");
	%>

	
	<div class="single-product">
		<div class="row">
			<div class="col-2">
				<div id="product-img" style="background-image: url(<%=product.getUrl()%>)"></div>
			</div>
			<div class="col-2">
				<div class="product-text">
					<h1><%=product.getName()%></h1>
					<p>
						<%=product.getShortDescription()%>
					</p>
					<div class="price-content">
						<h4>&euro; <%=product.getRealPriceString()%></h4>
						<p>IVA inclusa</p><br>
					</div>
						<%
							if(product.getQuantity() > 0)
							{
						%>
								<p class="available">Disponibile<i class="fas fa-check"></i></p> 
			
								<button  value="<%=product.getCode()%>" class="addCart">ACQUISTA</button>
						<%
							}
							else
							{
						%>		
							<p class="available">Non disponibile<i class="fas fa-times"></i></p>
							<button  class="product-out-of-stock">ACQUISTA</button>
						<%	
							}
						%>
						
						<p><%=product.getDescription()%>.</p>
						
						<div class="product-icons">
							<ul>
								<li>
									<i class="fas fa-shipping-fast"></i>Spedizione gratis in tutta Italia.
								</li>
								<li>
									<i class="fas fa-envelope"></i>Supporto clienti 24/24.
								</li>
								<li>
									<i class="fas fa-undo-alt"></i>Reso garantito.
								</li>
							</ul>
						</div>
					

				</div>
				


			</div>
		</div>
	</div>

	<script>

    $(document).ready(function() {

        $(".addCart").click(function(){
            var val = $(this).val();
            $.ajax({
                async: true,
                url: "./ProductControl",
                type: "POST",
                datatType: "json",
                data: {id : val},
                success: function(data){

                    var span = document.getElementById('cartSize');
                    while( span.firstChild ) {
                        span.removeChild( span.firstChild );
                    }
                    span.appendChild(document.createTextNode(data));


                }
            },0);

        });

    });

</script>
	
	<%@include file="../fragments/footer.jsp" %>
</body>
</html>