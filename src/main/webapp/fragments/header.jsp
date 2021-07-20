<%@page import="beans.Cart"%>
<header>

<%
	Cart cart2 =(Cart) session.getAttribute("cart");
	int size = 0;
	if(cart2 != null){
	    size = cart2.getTotalQuantity();
	}

%>
		
		
		<nav class="mobile-menu">
			<span class="close-burger">&times;</span>
			<ul>
				<li><a href="<%=request.getContextPath()%>/common_pages/home.jsp">Home</a></li>
				<li class="has-children"><a href="<%=request.getContextPath()%>/common_pages/catalogue.jsp">Negozio</a>
					<ul class="sub-menu-mobile">
						<li><a href="#">Anelli</a></li>
						<li><a href="#">Bracciali</a></li>
						<li><a href="#">Collane & Pendenti</a></li>
						<li><a href="#">Orecchini</a></li>
					</ul>
				</li>
				<li class="has-children"><a href="#">Collezioni</a>
					<ul class="sub-menu-mobile">
						<li><a href="#">Naturalis Mundi</a></li>
						<li><a href="#">The Blue</a></li>
						<li><a href="#">Ornamental</a></li>
						<li><a href="#">Butterfly Effect</a></li>
					</ul>
				</li>
				<li><a href="">About</a></li>
				<li><a href="">Contatti</a></li>
																				
			</ul>
		
		</nav>
		
		<div id="search-overlay" class="overlay">
			<div class="overlay-content">
    			<form id="search-container" action="<%=request.getContextPath()%>/Search">
      				<input type="text" placeholder="Cerca..." name="search">
      				<input type="hidden" name="action" value="common">
      				<button type="submit"><i class="fa fa-search"></i></button>
   				</form>
  			</div>
		</div>
		
		
		<div class="logo">
			<a href="<%=request.getContextPath()%>/common_pages/home.jsp">
				<img class="logo-full"src="<%=request.getContextPath()%>/images/logo.png">
			</a>
			
			<a href="<%=request.getContextPath()%>/common_pages/home.jsp">
				<img class="logo-mobile" src="<%=request.getContextPath()%>/images/logo-mobile.png">
			</a>
		</div>
		
		<nav class="menu">
			<ul>
				<li><a href="<%=request.getContextPath()%>/common_pages/home.jsp">Home</a></li>
				<li class="has-children"><a href="<%=request.getContextPath()%>/ProductControl?categoryId=-1">Negozio <i class="fas fa-caret-down"></i></a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=1">Anelli</a></li>
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=2">Bracciali</a></li>
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=3">Collane & Pendenti</a></li>
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=4">Orecchini</a></li>
					</ul>
				</li>
				<li class="has-children"><a href="">Collezioni <i class="fas fa-caret-down"></i></a>
					<ul class="sub-menu">
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=5">Naturalis Mundi</a></li>
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=6">The Blue</a></li>
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=7">Ornamental</a></li>
						<li><a href="<%=request.getContextPath()%>/ProductControl?categoryId=8">Butterfly Effect</a></li>
					</ul>
				</li>
				<li><a href="">About</a></li>
				<li><a href="">Contatti</a></li>
																				
			</ul>
		</nav>
		
		<div class="header-icons">
			<a class="search-btn" title="Cerca"><img src="<%=request.getContextPath()%>/images/icons/search.png"></a>
			
			<%
			if(session.getAttribute("user")==null && session.getAttribute("adminRoles")==null ) 
			  {
			%>

			<a href="<%=request.getContextPath()%>/common_pages/login_user.jsp" title="Login"><img src="<%=request.getContextPath()%>/images/icons/user.png"></a>
					
			
			<%
			}
			%>
			
			<%
			if(session.getAttribute("user")!=null) 
		  	{
			%>
			<a href="<%=request.getContextPath()%>/user_pages/dashboard.jsp" title="Dashboard account"><img src="<%=request.getContextPath()%>/images/icons/user.png"></a>
			
			<%
			}
			%>
			<%
			if(session.getAttribute("adminRoles")!=null) 
		  	{
			%>
			<a href="<%=request.getContextPath()%>/admin_pages/dashboard.jsp" title="Dashboard account"><img src="<%=request.getContextPath()%>/images/icons/settings.png"></a>
			
			<%
			}
			%>
			<a href="#" title="Wishlist"><img src="<%=request.getContextPath()%>/images/icons/wishlist.png"></a>
			<div class="shopping-bag">
				<a href="<%=request.getContextPath()%>/common_pages/cart.jsp"><img src="<%=request.getContextPath()%>/images/icons/shopping-bag.png" title="Carrello"></a>
				<span class="cart-basket">
					<%=size%>
				</span>
			</div>
		</div>
		
		<div class="burger">
			<div class="line1"></div>
			<div class="line2"></div>
			<div class="line3"></div>
		</div>

	
		<script>

			
			$(document).ready(function(){


				$("#search-overlay").click(function(event){
					if(event.target.id === "search-overlay"){
						$("#search-overlay").css({"visibility" : "hidden", "opacity" : "0"});
					}
				});

				
				$(".search-btn").click(function(){
					$("#search-overlay").css({"visibility" : "visible", "opacity" : "1"});
				});
				
			})
			
			
			$(document).ready(function(){
				$(".burger").click(function(){
					$(".mobile-menu").addClass("active");
					$("body").addClass("burger-open");
				})
				
				$(".close-burger").click(function(){
					$(".mobile-menu").removeClass("active");
					$("body").removeClass("burger-open");
				})
			})
		</script>
</header>

