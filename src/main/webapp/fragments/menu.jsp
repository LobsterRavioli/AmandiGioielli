<nav id="menu">
    <ul id="tabs">
    
    
    
    <%
    	if(session.getAttribute("user") == null)
    	{
    %>
        <li>
        	<a href="<%=request.getContextPath()%>/common_pages/login_user.jsp">Log-in</a>
        </li>

		 <li>
        	<a href="<%=request.getContextPath()%>/common_pages/registration.jsp">Registrati</a>
         </li>
	<%
    	}
	%>
    
      	<li>
            <a href="<%=request.getContextPath()%>/common_pages/home.jsp">Home</a>
        </li>
        
       	<li>
            <a href="<%=request.getContextPath()%>/common_pages/catalogue.jsp">Catalogo</a>
        </li>

        

        
        <li style="float:right; background-color:darkcyan;">
            <a href="<%=request.getContextPath()%>/common_pages/cart.jsp">Carrello</a>
        </li>
    <%
    	if(session.getAttribute("user") != null)
    	{
    %>

		<li>
			<a href="<%=request.getContextPath()%>/user_pages/orders.jsp">Ordini</a>
		</li>
		
		<li>
			<a href="<%=request.getContextPath()%>/LogOutControl">Logout</a>
		</li>
	<%
    	}
	%>
      
        
    </ul>
</nav>