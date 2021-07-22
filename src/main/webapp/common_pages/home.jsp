<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,beans.*"%>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Amandi Gioielli - Home</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
   	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/all.min.css" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel='shortcut icon' type='image/x-icon' href='./images/favicon.ico'/>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>


</head>

<%

	Collection<?> products = (Collection<?>) request.getAttribute("products");

	String error = (String)request.getAttribute("error");
	
	if(products == null && error == null) 
	{
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/ProductControl?page=home"));
		return;
	}
%>


	<%@include file="../fragments/header.jsp"%>
	
	<div class="row hero-section">
		<div class="col-2">
			<div class="column hero-section">
				<div class="value-proposition">
						<h1>Gioielli in Argento Sterling 925</h1><br>
						<div class="uppercase-hero">
							<span class="hero-100"> <h2> 100% </h2></span><h2>HANDMADE <br>
							e MADE IN ITALY.</h2><br>
						</div> 
				</div>
				<div class="hero-description">
					<p>
						I gioielli Amandi interpretano le mille sfaccettature che 
						compongono <strong>la nostra affascinante penisola,</strong> 
						elevando le nostre storiche tradizioni ad un livello internazionale.
					</p>
				</div>
				
				<a class="cta" href="#">scopri di più</a>
	
				<p class="free-shipping">Spedizioni e resi GRATIS</p>
			</div>
			
			
		</div>
		<div class="col-2">
			<img src="<%=request.getContextPath()%>/images/hero-section.png" alt="Alla ricerca del bello">
		</div>
	</div>
	
	
	<div class="mobile-row hero-section">
		<div class="column hero-section">
			<div class="value-proposition">
				<h1>Gioielli in Argento Sterling 925</h1><br>
					<div class="uppercase-hero">
						<span class="hero-100"><h2>100%</h2></span><h2>HANDMADE <br>
						e MADE IN ITALY.</h2><br>
					</div> 
			</div>
			<div class="hero-description">
				<p>
					I gioielli Amandi interpretano le mille sfaccettature che 
					compongono <strong>la nostra affascinante penisola,</strong> 
					elevando le nostre storiche tradizioni ad un livello internazionale.
				</p>
			</div>
				
			<a class="cta" href="#">scopri di più</a>
		</div>
	</div>
	
	<div class="main-content">
		<div class="small-container">
				<h2>MUST HAVE</h2>
				<div class="row">
					
					<%
					if(products != null && products.size() > 0) 
					{
						Iterator<?> it = products.iterator();
						for(int i=0; i<4; i++)
						{
						ProductBean bean = (ProductBean) it.next();
				
					%>
							<div class="col-4">
								<a href="<%=request.getContextPath()%>/ProductControl?action=details&id=<%=bean.getCode()%>"><img src="<%=bean.getUrl()%>"></a>
								<h4><%=bean.getName()%></h4>
								<div class="product-details">
									<p><%=bean.getRealPriceString()%> &euro;</p>
									<a href="<%=request.getContextPath()%>/ProductControl?action=details&id=<%=bean.getCode()%>" class="add-cart-home">Dettagli</a>
								</div>
							</div>
						
					<%
						}
					}
					%>
				</div>

		</div>
		
		<div class="other-content">
			<div class="chiasma">
				<div class="text">
					<h3>SAMPLE TEXT</h3>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ornare dui et pellentesque varius. 
						Ut eu velit nulla. Aliquam a pretium felis. Curabitur augue tellus, varius ut elementum vitae, porttitor sed tellus. 
						Pellentesque molestie euismod nunc.  
					</p>
				</div>
				<div class="image">
					<img src="<%=request.getContextPath()%>/images/ornamental.jpg">
				</div>
			</div>
			
			<div class="parallax">
				<div class="text parallax">
					<h3>SAMPLE TEXT</h3>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ornare dui et pellentesque varius. 
						Ut eu velit nulla. Aliquam a pretium felis. Curabitur augue tellus, varius ut elementum vitae, porttitor sed tellus. 
						Pellentesque molestie euismod nunc. Vivamus sit amet tortor a tortor aliquam vestibulum eu et dui. 
						Donec congue massa sollicitudin odio consectetur dignissim. Maecenas id eleifend quam. 
						Phasellus pharetra elementum tortor vel convallis. Ut ac odio lorem. 
					</p>
				</div>
			</div>
			
			
			<div class="chiasma">
				<div class="image">
					<div class="image">
						<img src="<%=request.getContextPath()%>/images/butterfly-effect.jpg">
					</div>
				</div>
				<div class="text">
					<h3>SAMPLE TEXT</h3>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ornare dui et pellentesque varius. 
						Ut eu velit nulla. Aliquam a pretium felis. Curabitur augue tellus, varius ut elementum vitae, porttitor sed tellus. 
						Pellentesque molestie euismod nunc.  
					</p>
				</div>
			</div>
			
		</div>
	</div>
	
	
	

	<%@include file="../fragments/footer.jsp" %>

<body>
    
    
</body>
</html>